package common.struts.helpers.user;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import common.ehcache.exception.CacheElementNotFound;
import common.hibernate.bf.user.User;
import common.hibernate.bf.user.UserRole;
import common.struts.actionforms.user.ChangePwdForm;
import common.struts.actionforms.user.ForgotPwdForm;
import common.struts.actionforms.user.LoginForm;
import common.struts.actionforms.user.UserForm;
import common.struts.actionforms.user.UserRoleForm;
import common.util.DateUtil;
import common.util.HibernateUtil;
import common.util.RetriveContextData;
import common.util.Utility;

import cricket.hibernate.bf.team.UserTeam;
import cricket.struts.actionforms.common.SeriesForm;
import cricket.struts.helpers.common.GenerateMailHelper;

public class LoginDBHelper
{

	public static String handleForgotPWD(ForgotPwdForm forgetPwdForm) throws Exception
	{

		String emailId = forgetPwdForm.getEmailId();

		String message = "";

		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(User.class);

		criteria.add(Restrictions.like("emailId", emailId));

		List<User> users = (List<User>) criteria.list();
		if (!Utility.isCollectionNullOrEmpty(users))
		{
			for (User tempUser : users)
			{
				if (tempUser.getEmailId().equalsIgnoreCase(emailId))
				{
					GenerateMailHelper.sendMailForgotPassword(tempUser);
					message = "Your Credentials has sent to this mail Id, Please check!";
				}
			}

		}
		else
		{
			message = "Enter details are not matched";
		}

		return message;

	}

	public static void changepwdmethod(ChangePwdForm changePwdForm, UserForm loginUser) throws Exception
	{

		String opwd = changePwdForm.getOldPwd();
		String npwd = changePwdForm.getNewPwd();
		String cpwd = changePwdForm.getCfPwd();
		if (!npwd.equals(cpwd))
		{
			throw new Exception("Confirm Password Not Matched. Please try again.");
		}
		if (opwd.equals(cpwd))
		{
			throw new Exception("New Password is same as Old Password, Please Enter again.");
		}
		List<User> list = null;
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("id", loginUser.getId()));

		list = (List<User>) criteria.list();

		if (list.size() > 0)
		{

			User luser = list.get(0);
			if (!luser.getPassword().equals(opwd))
			{
				throw new Exception("Entered Password is Wrong. Please try again.");
			}
			luser.setPassword(npwd);

			session.update(luser);
		}
	}

	public static void addLoginDetails(UserForm newLoginForm) throws Exception
	{
		Session session = HibernateUtil.getSession();
		User user = new User();
		user.setName(newLoginForm.getName());
		// user.setLoginName(newLoginForm.getLoginName());
		user.setLoginName(newLoginForm.getEmailId());
		checkUserAlreadyExists(user);
		String pwd = newLoginForm.getPwd();
		user.setContactNo(newLoginForm.getContactNo());
		user.setAddress(newLoginForm.getAddress());
		user.setEmailId(newLoginForm.getEmailId());
		user.setGender(newLoginForm.getGender());
		String repwd = newLoginForm.getReEnterPwd();
		Pattern p = Pattern.compile("^(?=.*[a-zA-Z])[a-zA-Z0-9_-]{6,}$");
		Matcher m = p.matcher(pwd);
		boolean valid = m.matches();
		if (!valid)
		{
			throw new Exception("Password requires minimum six alpha numeric characters.  ");
		}
		if (repwd != null && repwd.equals(pwd))
		{
			user.setPassword(newLoginForm.getPwd());
		}
		else
		{
			throw new Exception("Passwords not are matched, Please Re-Enter again. ");
		}
		if (newLoginForm.getDob() != null)
		{
			Date date = null;
			try
			{
				date = DateUtil.getFormattedDateFromString(newLoginForm.getDob());
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

		try
		{
			GenerateMailHelper.sendMailRegisterUser(user);
		}
		catch (Exception e)
		{
			//throw new Exception("Please enter working mail id.");
		}
		session.save(user);
	}

	private static void setUserRoles(User user, UserForm newLoginForm)
	{
		List<UserRole> userRoles = new ArrayList<UserRole>();
		if (newLoginForm.getUserRoles() == null
				|| (newLoginForm.getUserRoles() != null && newLoginForm.getUserRoles().size() > 0))
		{
			UserRole userRole = getUserRole(90L);
			userRoles.add(userRole);
			userRole = getUserRole(101L);
			userRoles.add(userRole);
		}
		else
		{
			for (UserRoleForm userRoleForm : newLoginForm.getUserRoles())
			{
				userRoles.add(getUserRole(userRoleForm.getId()));
			}
		}
		user.setUserRoles(userRoles);
	}

	private static void checkUserAlreadyExists(User user) throws Exception
	{
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(User.class);
		// criteria.add(Restrictions.eq("loginName", user.getLoginName()));
		criteria.add(Restrictions.or(Restrictions.eq("loginName", user.getLoginName()),
				Restrictions.eq("emailId", user.getLoginName())));

		List<UserRole> list = criteria.list();
		if (list.size() > 0)
		{
			throw new Exception("User with this emailId already registered, Retrive the account using forgot password.");
		}
	}

	public static UserRole getUserRole(Long ordinaryUser)
	{
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(UserRole.class);
		criteria.add(Restrictions.eq("id", ordinaryUser));
		List<UserRole> list = criteria.list();
		return list.get(0);
	}

	public static List<UserRoleForm> getDefaultUserRoleForms()
	{
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(UserRole.class);
		criteria.add(Restrictions.eq("defaultRole", true));
		List<UserRole> list = criteria.list();
		List<UserRoleForm> userRoleforms = new ArrayList<UserRoleForm>();
		if (list != null)
		{
			for (UserRole userRole : list)
			{
				userRoleforms.add(userRole.getUserRoleForm());
			}
		}
		return userRoleforms;
	}

	public static UserForm compareLoginDetails(LoginForm loginForm) throws CacheElementNotFound
	{

		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(User.class);
		if (loginForm.getLoginName() != null)
		{
			criteria.add(Restrictions.or(Restrictions.eq("loginName", loginForm.getLoginName()),
					Restrictions.eq("emailId", loginForm.getLoginName())));
		}

		Collection<User> loginUsers = criteria.list();
		UserForm userForm = null;
		if (!Utility.isCollectionNullOrEmpty(loginUsers))
		{
			for (User user : loginUsers)
			{

				if (user.getPassword().equals(loginForm.getPwd()))
				{
					user.setCount(user.getCount() + 1);
					// session.save(user);
					if (!user.isHadCSTeam())
					{
						SeriesForm seriesForm = new RetriveContextData().getSeriesFormById(3);
						if (seriesForm.isActive())
						{
							// commented the below for time being until new
							// series launched.
							setCurrentSeriesRole(user);
							user.setHadCSTeam(true); // setting current series
														// team.
							session.save(user);
						}
					}
				}
				if (user.getLoginName().equalsIgnoreCase(loginForm.getLoginName())
						|| user.getEmailId().equalsIgnoreCase(loginForm.getLoginName()))
				{
					userForm = user.getUserForm();
					break;
				}

			}
		}
		return userForm;
	}

	public static void updateUserforAcceptence(UserForm user)
	{
		// TODO Auto-generated method stub

	}

	public static UserRole findUserRoleById(long roleId)
	{
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(UserRole.class);
		criteria.add(Restrictions.eq("id", roleId));
		List<UserRole> list = criteria.list();
		if (list != null && list.size() > 0)
		{
			return list.get(0);
		}
		return null;
	}

	public static UserForm updateUserProfile(UserForm userForm) throws Exception
	{
		Session session = HibernateUtil.getSession();
		User user = findUserById(userForm.getId());
		user.setName(userForm.getName());
		user.setContactNo(userForm.getContactNo());
		user.setAddress(userForm.getAddress());
		user.setEmailId(userForm.getEmailId());
		user.setGender(userForm.getGender());
		if (userForm.getDob() != null)
		{
			Date date = null;
			try
			{
				date = DateUtil.getFormattedDateFromString(userForm.getDob());
			}
			catch (ParseException e)
			{
				throw new Exception("Please enter correct date format. ");
			}
			user.setDob(date);
		}
		session.save(user);
		return user.getUserForm();
	}

	public static User findUserById(Long id)
	{
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("id", id));
		List<User> list = criteria.list();
		if (list != null && list.size() > 0)
		{
			return list.get(0);
		}
		return null;
	}

	public static UserRole getUserRoleBySeriesId(Long id)
	{
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(UserRole.class);
		criteria.add(Restrictions.eq("id", id));
		List<UserRole> list = criteria.list();
		if (list != null && list.size() > 0)
		{
			return list.get(0);
		}
		return null;
	}

	public static void setLogedInUserRoles(UserForm loginUser)
	{
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(UserTeam.class);
		criteria.add(Restrictions.eq("userId", loginUser.getId()));
		Collection<UserTeam> list = criteria.list();
		for (UserTeam userTeam : list)
		{
			if (userTeam != null)
			{
				// loginUser.getUserRoles().add(getUserRoleBySeriesId(userTeam.getSeriesId()));
			}
		}
		// Collections.sort(loginUser.getUserRoles(),new
		// UserRoleFormComparator<UserRoleForm>());
	}

	public static void setCurrentSeriesRole(User user)
	{
		List<UserRole> userRoles = user.getUserRoles();
		boolean hasIPLRole = false;
		for (UserRole role : userRoles)
		{
			if (role != null && role.getId().intValue() == 109)
			{
				hasIPLRole = true;
				break;
			}
		}
		if (!hasIPLRole)
		{
			user.getUserRoles().add(getUserRole(109L));
		}

	}
}
