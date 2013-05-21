package common.struts.helpers.user;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import common.hibernate.bf.user.User;
import common.hibernate.bf.user.UserRole;
import common.struts.actionforms.user.ChangePwdForm;
import common.struts.actionforms.user.ForgotPwdForm;
import common.struts.actionforms.user.LoginForm;
import common.struts.actionforms.user.UserForm;
import common.struts.actionforms.user.UserRoleForm;
import common.util.DateUtil;
import common.util.SessionFactoryUtil;

public class LoginDBHelper_old
{
  // static ApplicationContext ctx = null;

  public static List<User> handleForgotPWD(ForgotPwdForm forgetPwdForm) throws Exception
  {

  String userName = forgetPwdForm.getLoginName();
  Date date = null;
  try
  {
    date = DateUtil.getFormattedDateFromString(forgetPwdForm.getDob());
   //    System.out.println("***date:****" + date);
  }
  catch (ParseException e)
  {
    throw new Exception("Please enter correct date format.");
  }
  String pwd = forgetPwdForm.getPwd();

  Session session = SessionFactoryUtil.getInstance().getCurrentSession();
  Transaction tx = session.beginTransaction();
  Criteria criteria = session.createCriteria(User.class);

  criteria.add(Restrictions.like("loginName", userName, MatchMode.ANYWHERE));
  criteria.add(Restrictions.eq("dob", date));

  List<User> list = (List<User>) criteria.list();
  if (list.size() > 0)
  {
    User luser = list.get(0);
    if (!forgetPwdForm.getPwd().trim().equals(forgetPwdForm.getReEnteredPwd().trim()))
    {
      throw new Exception("Entered passwords are not matched, Please Eenter again.");
    }
    //System.out.println("******" + luser.toString());
    luser.setPassword(pwd);
    session.update(luser);
    // session.flush();
    tx.commit();
  }

  return list;

  }

  public static void changepwdmethod(ChangePwdForm changePwdForm, User loginUser) throws Exception
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
  Session session = SessionFactoryUtil.getInstance().getCurrentSession();
  Transaction tx = session.beginTransaction();
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
    // session.flush();
    tx.commit();
  }
  }

  public static void addLoginDetails(UserForm newLoginForm) throws Exception
  {

  User user = new User();
  user.setName(newLoginForm.getName());
  user.setLoginName(newLoginForm.getLoginName());
  checkUserAlreadyExists(user);
  String pwd = newLoginForm.getPwd();
  user.setContactNo(newLoginForm.getContactNo());
  user.setAddress(newLoginForm.getAddress());
  user.setEmailId(newLoginForm.getEmailId());
  String repwd = newLoginForm.getReEnterPwd();
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
  setUserRoles(user, newLoginForm);
  Session session = SessionFactoryUtil.getInstance().getCurrentSession();
  Transaction tx = session.beginTransaction();
  session.save(user);
  session.flush();
  tx.commit();
  // session.close();
  }

  private static void setUserRoles(User user, UserForm newLoginForm)
  {
  List<UserRole> userRoles = new ArrayList<UserRole>();
  if (newLoginForm.getUserRoles() == null || (newLoginForm.getUserRoles() != null && newLoginForm.getUserRoles().size() > 0))
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
  Session session = SessionFactoryUtil.getInstance().openSession();
  session.beginTransaction();
  Criteria criteria = session.createCriteria(User.class);
  criteria.add(Restrictions.eq("loginName", user.getLoginName()));
  List<UserRole> list = criteria.list();
  if (list.size() > 0)
  {
    throw new Exception("User alreay exits, Select other User Name.");
  }
  }

  public static UserRole getUserRole(Long ordinaryUser)
  {
  Session session = SessionFactoryUtil.getInstance().openSession();
  session.beginTransaction();
  Criteria criteria = session.createCriteria(UserRole.class);
  criteria.add(Restrictions.eq("id", ordinaryUser));
  List<UserRole> list = criteria.list();
  return list.get(0);
  }

  public static List<UserRoleForm> getDefaultUserRoleForms()
  {
  Session session = SessionFactoryUtil.getInstance().openSession();
  session.beginTransaction();
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

  public static void main(String args[])
  {

  }

  public static List<User> compareLoginDetails(LoginForm loginForm)
  {

  Session session = SessionFactoryUtil.getInstance().openSession();
  session.beginTransaction();
  Criteria criteria = session.createCriteria(User.class);
  if (loginForm.getLoginName() != null)
  {
    criteria.add(Restrictions.eq("loginName", loginForm.getLoginName()));
  }

  return (List<User>) criteria.list();
  }

  public static void updateUserforAcceptence(UserForm user)
  {
  // TODO Auto-generated method stub

  }

  public static UserRole findUserRoleById(long roleId)
  {
  Session session = SessionFactoryUtil.getInstance().openSession();
  session.beginTransaction();
  Criteria criteria = session.createCriteria(UserRole.class);
  criteria.add(Restrictions.eq("id", roleId));
  List<UserRole> list = criteria.list();
  if (list != null && list.size() > 0)
  {
    return list.get(0);
  }
  return null;
  }
}
