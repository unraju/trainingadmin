package quiz.spring.controler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import quiz.spring.form.QuizTO;
import quiz.spring.hibernate.bf.Quiz;

import common.util.HibernateUtil;


public class QuizService
{

	//private QuizDAO quizDAO;

	public void addQuiz(QuizTO quizTO)
	{

		Session session = HibernateUtil.getSession();
		if (quizTO.getId() == null || quizTO.getId() == 0)
		{
			System.out.println("### Quiz Add  ###"+quizTO.getQuiz());
			session.save(quizTO.getQuiz());
		}
		else
		{
			Quiz quiz = fetchQuiz(quizTO.getId());
			quizTO.copyDate(quiz);
			System.out.println("### Quiz Update  ###"+quiz);
			session.update(quiz);
		}

	}

	public void updateQuiz(QuizTO quizTO)
	{
		// TODO Auto-generated method stub

	}

	public void deleteQuiz(QuizTO quizTO)
	{
		// TODO Auto-generated method stub

	}

	public List<QuizTO> fetchQuizDetails()
	{
		List<QuizTO> quizTOs = new ArrayList<QuizTO>();
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(Quiz.class);
		criteria.addOrder(Order.desc("quizNo"));
		Collection<Quiz> list = criteria.list();
		for (Quiz quiz : list)
		{
			quizTOs.add(quiz.getQuizTO());
		}
		return quizTOs;
		
	}

	public Quiz fetchQuiz(long quizId)
	{
		Quiz quiz = new Quiz();
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(Quiz.class);
		criteria.add(Restrictions.eq("id", quizId));
		Collection<Quiz> list = criteria.list();
		for (Quiz tempQuiz : list)
		{
			quiz = tempQuiz;
		}
		return quiz;
	}
}
