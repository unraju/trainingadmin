package webservices.restful.quiz;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import quiz.spring.hibernate.bf.Quiz;
import quiz.spring.hibernate.bf.QuizSchedule;
import quiz.spring.hibernate.bf.UserQuiz;
import webservices.restful.JAXBMarshellingHelper;
import webservices.restful.user.UserWSTO;

import common.util.Constants;
import common.util.DateUtil;
import common.util.HibernateUtil;

@Path("/quiz")
public class QuizRestfulWS
{

	@GET
	@Path("/getQuizs/{userRef}")
	@Produces(MediaType.TEXT_XML)
	public String getUserQuizDetails(@PathParam("userRef") String userRef)
	{
		QuizDetailsTO quizDetailsTO;
		try
		{
			quizDetailsTO = getQuizDetails(userRef);
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			quizDetailsTO = new QuizDetailsTO();
			quizDetailsTO.setErrorMessage(e.getLocalizedMessage());
		}

		return JAXBMarshellingHelper.marshellObject(quizDetailsTO, QuizDetailsTO.class);// bestPlayers;;;//
																						// bestPlayers;
	}

	@GET
	@Path("/updateQScore/{userRef}/{answer}")
	@Produces(MediaType.TEXT_XML)
	public String updateQuizScore(@PathParam("userRef") String userRef, @PathParam("answer") String answer)
	{
		// if answer == 0 --> wrong answer
		// if answer == 1 --> Correct answer
		Session session = HibernateUtil.getSession();
		// transaction = session.beginTransaction();

		UserQuiz userQuiz = null;
		UserWSTO userWSTO = null;
		if (userRef != null)
		{
			userQuiz = getUserQuiz(userRef);
		}
		userQuiz.setAnsweredCount(userQuiz.getAnsweredCount() + 1);
		userQuiz.setScore(userQuiz.getScore() + Long.parseLong(answer));
		userQuiz.setAnswered(true);

		session.save(userQuiz);

		userWSTO = userQuiz.getUserWSTO();

		return JAXBMarshellingHelper.marshellObject(userWSTO, UserWSTO.class);
	}

	private UserQuiz getUserQuiz(String userRef)
	{

		Criteria criteria = HibernateUtil.getSession().createCriteria(UserQuiz.class);
		Criteria userCriteria = criteria.createCriteria("user");
		userCriteria.add(Restrictions.eq("userRef", userRef));

		return (UserQuiz) criteria.list().get(0);
	}

	public QuizDetailsTO getQuizDetails(String userRef) throws Exception
	{
		QuizDetailsTO quizDetails = new QuizDetailsTO();

		List<QuizWSTO> quizTOs = new ArrayList<QuizWSTO>();
		List<UserQuizScoresWSTO> userScoreTOs = new ArrayList<UserQuizScoresWSTO>();

		Session session = HibernateUtil.getSession();
		// transaction = session.beginTransaction();

		Criteria criteriaSchedule = session.createCriteria(QuizSchedule.class);
		criteriaSchedule.add(Restrictions.eq("live", true));
		Collection<QuizSchedule> listSchedule = criteriaSchedule.list();
		QuizSchedule quizSchedule = new QuizSchedule();
		for (QuizSchedule tempQuizSchedule : listSchedule)
		{
			quizSchedule = tempQuizSchedule;
			break;
		}

		Long todayQuizNo = checkQuizValid(quizSchedule);

		Criteria criteria = session.createCriteria(Quiz.class);
		criteria.add(Restrictions.le("quizNo", todayQuizNo));
		criteria.addOrder(Order.desc("quizNo"));
		Collection<Quiz> list = criteria.list();
		for (Quiz quiz : list)
		{
			quizTOs.add(quiz.getQuizWSTO());
			if (quizTOs.size() > 10)
			{
				break;
			}

		}
		quizDetails.setQuizData(quizTOs);

		criteria = session.createCriteria(UserQuiz.class);
		criteria.addOrder(Order.desc("score"));
		Collection<UserQuiz> userQuizs = criteria.list();
		for (UserQuiz quiz : userQuizs)
		{
			userScoreTOs.add(quiz.getUserScoreWSTO());
		}
		quizDetails.setQuizScores(userScoreTOs);

		if (userRef != null)
		{
			criteria = session.createCriteria(UserQuiz.class);
			criteria.createCriteria("user").add(Restrictions.eq("userRef", userRef));
			userQuizs = criteria.list();
			for (UserQuiz quiz : userQuizs)
			{
				quizDetails.setUserWSTO(quiz.getUserWSTO());
				break;
			}
		}
		// /transaction.commit();
		return quizDetails;
	}

	private Long checkQuizValid(QuizSchedule quizSchedule) throws ParseException
	{
		Long quizNo = quizSchedule.getQuizNo();
		boolean valid = false;
		Date now = DateUtil.getCurrentDateAsTimestampinGMT0530();

		if (now.after(quizSchedule.getStartDate()) && now.before(quizSchedule.getEndDate()))
		{
			valid = true;
		}

		if (!valid)
		{
			Session session = HibernateUtil.getSession();
			quizSchedule.setLive(false);
			session.save(quizSchedule);

			QuizSchedule newquizSchedule = new QuizSchedule();
			newquizSchedule.setLive(true);
			newquizSchedule.setQuizNo(quizSchedule.getQuizNo() + 1);
			newquizSchedule.setCreatedDate(now);
			newquizSchedule.setStartDate(now);
			newquizSchedule.setEndDate(DateUtil.getDateFromStringFormatDATE_TIME(DateUtil
					.getCurrentDateAsString(Constants.DATE_FORMAT_DD_MMM_YYYY) + " 23:59"));

			session.save(newquizSchedule);

			/*Query query = session.createSQLQuery("update user_quiz set answered = 'N' where answered= 'Y'");
			query.executeUpdate();

			// setting the expired quiz to live - false
			session.createSQLQuery("update quiz set live = 'N' where quizNo = " + quizSchedule.getQuizNo())
					.executeUpdate();

			// setting the new quiz to live - true
			session.createSQLQuery("update quiz set live = 'Y' where quizNo = " + newquizSchedule.getQuizNo())
					.executeUpdate();*/

			Criteria criteria = session.createCriteria(UserQuiz.class);
			criteria.add(Restrictions.eq("answered", true));
			Collection<UserQuiz> userQuizs = criteria.list();
			for (UserQuiz userQuiz : userQuizs)
			{
				userQuiz.setAnswered(false);
				session.save(userQuiz);
			}

			Quiz quizOld = getQuizByQuizNo(quizSchedule.getQuizNo());
			quizOld.setLive(false);
			session.save(quizOld);

			Quiz quizNew = getQuizByQuizNo(newquizSchedule.getQuizNo());
			quizNew.setLive(true);
			session.save(quizNew);

			quizNo = newquizSchedule.getQuizNo();
		}
		return quizNo;
	}

	private Quiz getQuizByQuizNo(Long quizNo)
	{
		Session session = HibernateUtil.getSession();
		Quiz quiz = null;
		Criteria criteria = session.createCriteria(Quiz.class);
		criteria.add(Restrictions.eq("quizNo", quizNo));
		Collection<Quiz> quizs = criteria.list();
		for (Quiz tquiz : quizs)
		{
			quiz = tquiz;
			break;
		}
		return quiz;
	}

}
