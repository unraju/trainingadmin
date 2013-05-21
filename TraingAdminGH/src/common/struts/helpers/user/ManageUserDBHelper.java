package common.struts.helpers.user;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import common.hibernate.bf.user.Activity;
import common.hibernate.bf.user.User;
import common.hibernate.bf.user.UserRole;
import common.struts.actionforms.user.ActivityForm;
import common.struts.actionforms.user.SearchRoleForm;
import common.struts.actionforms.user.SearchUserForm;
import common.struts.actionforms.user.UserForm;
import common.struts.actionforms.user.UserRoleForm;
import common.util.Constants;
import common.util.HibernateUtil;

import cricket.hibernate.bf.team.UserTeam;
import cricket.struts.helpers.team.ManageTeamDBHelper;

public class ManageUserDBHelper
{

  public static List<UserRoleForm> getAllUserRoles()
  {

    Session session = HibernateUtil.getSession();
    Criteria criteria = session.createCriteria(UserRole.class);
    criteria.addOrder(Order.asc("priority"));
    List<UserRole> list = criteria.list();
    List<UserRoleForm> userRoleForms = new ArrayList<UserRoleForm>();
    for (UserRole userRole : list)
    {
      userRoleForms.add(userRole.getUserRoleForm());
    }
    return userRoleForms;
  }

  public static List<ActivityForm> getAllRoleActivities()
  {

    Session session = HibernateUtil.getSession();
    Criteria criteria = session.createCriteria(Activity.class);
    criteria.addOrder(Order.asc("priority"));
    List<Activity> list = criteria.list();
    List<ActivityForm> userActivityForms = new ArrayList<ActivityForm>();
    for (Activity activity : list)
    {
      userActivityForms.add(activity.getActivityForm());
    }
    return userActivityForms;
  }

  public static List<User> searchUsers(SearchUserForm searchUserForm)
  {

    Session session = HibernateUtil.getSession();
    Criteria criteria = session.createCriteria(User.class);
    if (searchUserForm.getUserName() != null && !searchUserForm.getUserName().trim().equals(""))
    {
      criteria.add(Restrictions.like("name", searchUserForm.getUserName(), MatchMode.ANYWHERE));
    }
    if (searchUserForm.getLoginName() != null && !searchUserForm.getLoginName().trim().equals(""))
    {
      criteria.add(Restrictions.like("loginName", searchUserForm.getLoginName()));
    }

    if (searchUserForm.getRole() != null && !searchUserForm.getRole().trim().equals("ALL"))
    {
      Long roleId = Long.parseLong(searchUserForm.getRole());
      Criteria roleCriteria = criteria.createCriteria("userRoles");
      roleCriteria.add(Restrictions.eq("id", roleId));
    }
    criteria.add(Restrictions.eq("active", searchUserForm.isActive()));
    List<User> list = criteria.list();
    return list;
  }

  public static void deleteUser(Long userId)
  {

    // TODO Auto-generated method stub

  }

  public static void updateUserRoles(UserForm newLoginForm) throws Exception
  {
   Session session = HibernateUtil.getSession();
    User user = getUserById(newLoginForm.getId());
    UpdateUserRoles(user, newLoginForm);
    if (newLoginForm.getActive() == 'Y')
    {
      user.setActive(true);
    }
    else
    {
      user.setActive(false);
    }
    UserTeam userTeam = new ManageTeamDBHelper().getUserTeam(user.getId());
    if(userTeam != null)
    {
      userTeam.setActive(user.isActive());
      session.update(userTeam);
    }
   
    session.save(user);
  }

  private static User getUserById(Long id)
  {
    Session session = HibernateUtil.getSession();
    Criteria criteria = session.createCriteria(User.class);
    criteria.add(Restrictions.eq("id", id));
    List<User> list = criteria.list();
    return list.get(0);
  }

  private static void UpdateUserRoles(User user, UserForm newLoginForm)
  {
    List<UserRole> userRoles = new ArrayList<UserRole>();
    if (newLoginForm.getUserRoles() == null || (newLoginForm.getUserRoles() != null && newLoginForm.getUserRoles().size() == 0))
    {
      UserRole userRole = getUserRole(Constants.ORDINARY_USER);
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

  private static UserRole getUserRole(Long ordinaryUser)
  {
    Session session = HibernateUtil.getSession();
    Criteria criteria = session.createCriteria(UserRole.class);
    criteria.add(Restrictions.eq("id", ordinaryUser));
    List<UserRole> list = criteria.list();
    return list.get(0);
  }

  public static void deleteRole(Long id)
  {

    Session session = HibernateUtil.getSession();
    session.delete(getUserRoleById(id));
  }

  public static List<UserRole> searchRoles(SearchRoleForm searchRoleForm)
  {

    Session session = HibernateUtil.getSession();
    Criteria criteria = session.createCriteria(UserRole.class);
    criteria.addOrder(Order.asc("priority"));
    if (searchRoleForm.getRole() != null && !searchRoleForm.getRole().trim().equals(""))
    {
      criteria.add(Restrictions.like("role", searchRoleForm.getRole(), MatchMode.ANYWHERE));
    }
    /*
     * if (searchRoleForm.isActive()) { criteria.add(Restrictions.like("active",
     * "Y")); } else { criteria.add(Restrictions.like("active", "N")); }
     */
    if (searchRoleForm.getSelectedActivity() != null && !searchRoleForm.getSelectedActivity().trim().equals("ALL"))
    {
      Long roleId = Long.parseLong(searchRoleForm.getSelectedActivity());
      Criteria roleCriteria = criteria.createCriteria("activities");
      roleCriteria.add(Restrictions.eq("id", roleId));
    }
    List<UserRole> list = criteria.list();
    return list;
  }

  public static void updateRoleActivities(UserRoleForm userRoleForm)
  {
    UserRole userRole;
    if (userRoleForm.getId() != null && !(userRoleForm.getId().intValue() == 0))
    {
      userRole = getUserRoleById(userRoleForm.getId());
    }
    else
    {
      userRole = new UserRole();
    }
    userRole.setRole(userRoleForm.getRole());
    userRole.setDiscription(userRoleForm.getDiscription());
    userRole.setActive(userRoleForm.isActive());
    userRole.setUserAssociated(userRoleForm.isUserAssociated());
    userRole.setDefaultRole(userRoleForm.isDefaultRole());
    String priority  =  userRoleForm.getPriority();
    if(priority != null && !priority.trim().equalsIgnoreCase(""))
    {
      userRole.setPriority(Long.parseLong(priority));
    }
    updateRoleActivities(userRole, userRoleForm);
    Session session = HibernateUtil.getSession();
    session.save(userRole);
    session.flush();
    session.getTransaction().commit();
    userRoleForm.setId(userRole.getId());
  }

  private static void updateRoleActivities(UserRole userRole, UserRoleForm userRoleForm)
  {

    List<Activity> activities = new ArrayList<Activity>();
    if (userRoleForm.getActivities() != null && userRoleForm.getActivities().size() > 0)
    {
      for (ActivityForm activityForm : userRoleForm.getActivities())
      {
        Activity activity = getRoleActivityById(activityForm.getId());
        activities.add(activity);
      }
    }
    userRole.setActivities(activities);
  }

  private static Activity getRoleActivityById(Long id)
  {

    Session session = HibernateUtil.getSession();
    Criteria criteria = session.createCriteria(Activity.class);
    criteria.add(Restrictions.eq("id", id));
    List<Activity> list = criteria.list();
    return list.get(0);

  }

  private static UserRole getUserRoleById(Long id)
  {
    Session session = HibernateUtil.getSession();
    Criteria criteria = session.createCriteria(UserRole.class);
    criteria.add(Restrictions.eq("id", id));
    List<UserRole> list = criteria.list();
    return list.get(0);
  }

  public static void deleteActivity(Long id)
  {
    Session session = HibernateUtil.getSession();
    session.delete(getRoleActivityById(id));
  }

  public static void addUpdateActivity(ActivityForm activityForm)
  {
    Activity activity;
    if (activityForm.getId() != null && !(activityForm.getId().intValue() == 0))
    {
      activity = getRoleActivityById(activityForm.getId());
    }
    else
    {
      activity = new Activity();
    }
    activity.setActivity(activityForm.getActivity());
    activity.setActivityUrl(activityForm.getActivityUrl());
    activity.setPriority(activityForm.getPriority());
    activity.setActive(activityForm.isActive());
    Session session = HibernateUtil.getSession();
    session.save(activity);
    activityForm.setId(activity.getId());
  }

  public static void updateActivities(List<ActivityForm> activityList)
  {

    for (ActivityForm activityForm : activityList)
    {
      addUpdateActivity(activityForm);
    }
  }

}
