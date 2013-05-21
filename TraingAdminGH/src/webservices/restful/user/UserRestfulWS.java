package webservices.restful.user;

import java.text.ParseException;
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
import org.hibernate.criterion.Restrictions;

import quiz.spring.hibernate.bf.UserQuiz;
import webservices.restful.JAXBMarshellingHelper;

import common.hibernate.bf.user.User;
import common.hibernate.bf.user.UserRole;
import common.util.DateUtil;
import common.util.HibernateUtil;
import common.util.Utility;

import cricket.struts.helpers.common.GenerateMailHelper;

@Path("/user")
public class UserRestfulWS
{

	// Session session;
	// Transaction transaction;

	@GET
	@Path("/register/{userName}/{userId}/{password}/{emailId}/{phoneNo}/{dob}")
	@Produces(MediaType.TEXT_XML)
	public String registerUser(@PathParam("userName") String userName, @PathParam("userId") String userId,
			@PathParam("password") String password, @PathParam("emailId") String emailId,
			@PathParam("phoneNo") String phoneNo, @PathParam("dob") String dob)
	{

		UserWSTO userWSTO;
		try
		{
			userWSTO = registerUserDetails(userName, userId, password, emailId, phoneNo, dob);
			userWSTO.setUserErrorMessage("Thnanks for registering...!");
		}
		catch (Exception exception)
		{
			userWSTO = new UserWSTO();
			userWSTO.setUserErrorMessage(exception.getMessage());
			System.out.println("### Error - User Registration ###" + exception.getMessage());
			// TODO Auto-generated catch block
			// exception.printStackTrace();
		}

		return JAXBMarshellingHelper.marshellObject(userWSTO, UserWSTO.class);
	}

	@GET
	@Path("/loginUser/{userId}/{password}")
	@Produces(MediaType.TEXT_XML)
	public String loginUser(@PathParam("userId") String userId, @PathParam("password") String password)
	{
		return JAXBMarshellingHelper.marshellObject(handleUserLogin(userId, password), UserWSTO.class);
	}

	private UserWSTO handleUserLogin(String userId, String password)
	{
		Session session = HibernateUtil.getSession();
		// transaction = session.beginTransaction();

		Criteria criteria = session.createCriteria(User.class);
		if (userId != null)
		{
			criteria.add(Restrictions.or(Restrictions.eq("loginName", userId), Restrictions.eq("emailId", userId)));
		}

		Collection<User> loginUsers = criteria.list();
		UserWSTO userWSTO = new UserWSTO();
		if (!Utility.isCollectionNullOrEmpty(loginUsers))
		{
			for (User user : loginUsers)
			{
				if (user.getPassword().equals(password))
				{
					criteria = session.createCriteria(UserQuiz.class);
					criteria.add(Restrictions.eq("userId", user.getId()));

					Collection<UserQuiz> userQuizs = criteria.list();
					if (userQuizs != null && userQuizs.size() > 0)
					{
						for (UserQuiz userQuiz : userQuizs)
						{
							userWSTO = userQuiz.getUserWSTO();
							break;
						}

					}
					else
					{
						UserQuiz userQuiz = new UserQuiz();
						if (user.getUserRef() == null)
						{
							user.setUserRef(UserUniqueReferenceNoGenrator.getUniquePassthroughId4());
							session.save(user);
						}
						userQuiz.setUser(user);
						userQuiz.setUserId(user.getId());
						session.save(userQuiz);
						userWSTO = userQuiz.getUserWSTO();
					}

				}
				else
				{
					userWSTO = new UserWSTO();
					userWSTO.setUserErrorMessage("Logindetails are not matched");
				}
				break;
			}
		}
		else
		{
			userWSTO = new UserWSTO();
			userWSTO.setUserErrorMessage("Logindetails are not matched");
		}

		return userWSTO;
	}

	private static UserWSTO registerUserDetails(String userName, String userId, String password, String emailId,
			String phoneNo, String dob) throws Exception
	{

		Session session = HibernateUtil.getSession();
		// Transaction transaction = session.beginTransaction();

		User user = new User();
		user.setName(userName);
		user.setLoginName(userId);

		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.or(Restrictions.eq("loginName", user.getLoginName()),
				Restrictions.eq("emailId", user.getLoginName())));
		List<UserRole> list = criteria.list();
		if (list.size() > 0)
		{
			throw new Exception(
					"User account already already exits with this mailId, Please use forgot service to retrive account.");
		}

		user.setContactNo(phoneNo);
		// user.setAddress(newLoginForm.getAddress());
		user.setEmailId(emailId);
		// user.setGender(newLoginForm.getGender());

		user.setPassword(password);

		if (dob != null)
		{
			Date date = null;
			try
			{
				date = DateUtil.getFormattedDateFromString(dob);
			}
			catch (ParseException e)
			{
				throw new Exception("Please enter correct date format. ");
			}
			user.setDob(date);
		}
		user.setCreatedDate(DateUtil.getCurrentDateAsTimestamp());
		user.setActive(true);
		user.setCount(0L);
		user.setHadCSTeam(false);
		user.setMailFailed(false);
		// setUserRoles(user, newLoginForm);

		user.setUserRef(UserUniqueReferenceNoGenrator.getUniquePassthroughId4());

		try
		{
			GenerateMailHelper.sendMailRegisterUser(user);
		}
		catch (Exception e)
		{
			//throw new Exception("Please enter working mail id.");
		}
		
		session.save(user);

		UserQuiz userQuiz = new UserQuiz();

		userQuiz.setUser(user);
		userQuiz.setUserId(user.getId());
		session.save(userQuiz);

		return userQuiz.getUserWSTO();
	}

	@GET
	@Path("/forgotPassword/{emailId}")
	@Produces(MediaType.TEXT_XML)
	public String forgotPassword(@PathParam("emailId") String emailId)
	{
		Session session = HibernateUtil.getSession();
		// Transaction transaction = session.beginTransaction();

		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("emailId", emailId));
		/*
		 * if (phoneNo != null && !phoneNo.trim().equals("")) {
		 * criteria.add(Restrictions.eq("contactNo", phoneNo)); } if (userId !=
		 * null && !userId.trim().equals("")) {
		 * criteria.add(Restrictions.eq("loginName", userId)); }
		 */
		Collection<User> users = criteria.list();
		UserWSTO userWSTO = new UserWSTO();
		if (!Utility.isCollectionNullOrEmpty(users))
		{
			for (User tempUser : users)
			{
				userWSTO = tempUser.getUserWSTO();
				GenerateMailHelper.sendMailForgotPassword(tempUser);
				userWSTO.setUserErrorMessage("Your Credentials sent to mail Id, Please check!");
				break;
			}

		}
		else
		{
			userWSTO.setUserErrorMessage("Enter details are not matched");
		}

		return JAXBMarshellingHelper.marshellObject(userWSTO, UserWSTO.class);
	}

	@GET
	@Path("/getUser/{userRef}")
	@Produces(MediaType.TEXT_XML)
	public String getUser(@PathParam("userRef") String userRef)
	{
		Session session = HibernateUtil.getSession();

		UserWSTO userWSTO = new UserWSTO();
		if (userRef != null)
		{
			Criteria criteria = session.createCriteria(UserQuiz.class);
			criteria.createCriteria("user").add(Restrictions.eq("userRef", userRef));
			Collection<UserQuiz> userQuizs = criteria.list();
			for (UserQuiz quiz : userQuizs)
			{
				userWSTO = quiz.getUserWSTO();
				break;
			}
		}
		return JAXBMarshellingHelper.marshellObject(userWSTO, UserWSTO.class);
	}

}
