package common.hibernate.bf.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import common.struts.actionforms.user.ActivityForm;
import common.struts.actionforms.user.UserRoleForm;

public class UserRole implements Serializable
{

  private Long id;

  private String role;

  private boolean active;

  private String discription;

  private List<Activity> activities;
  
  private boolean userAssociated;
  
  private boolean defaultRole;
  
  private Long priority = 0L;
  
  private Long seriesId;

  public Long getId()
  {

    return id;
  }

  public void setId(Long id)
  {

    this.id = id;
  }

  public String getDiscription()
  {

    return discription;
  }

  public void setDiscription(String discription)
  {

    this.discription = discription;
  }

  public String getRole()
  {

    return role;
  }

  public void setRole(String role)
  {

    this.role = role;
  }

  public boolean isActive()
  {

    return active;
  }

  public void setActive(boolean active)
  {

    this.active = active;
  }

  public List<Activity> getActivities()
  {

    return activities;
  }

  public void setActivities(List<Activity> activities)
  {

    this.activities = activities;
  }

  public Long getPriority()
  {
    return priority;
  }

  public void setPriority(Long priority)
  {
    this.priority = priority;
  }

  @Override
  public String toString()
  {

    StringBuffer sb = new StringBuffer();
    sb.append(" User Role ").append(role);
    return sb.toString();
  }

  
  public boolean isUserAssociated()
  {
    return userAssociated;
  }

  public void setUserAssociated(boolean userAssociated)
  {
    this.userAssociated = userAssociated;
  }

  public boolean isDefaultRole()
  {
    return defaultRole;
  }

  public void setDefaultRole(boolean defaultRole)
  {
    this.defaultRole = defaultRole;
  }

  public Long getSeriesId()
  {
  return seriesId;
  }

  public void setSeriesId(Long seriesId)
  {
  this.seriesId = seriesId;
  }

  public UserRoleForm getUserRoleForm()
  {

    UserRoleForm roleForm = new UserRoleForm();
    roleForm.setId(this.id);
    roleForm.setRole(this.role);
    roleForm.setDiscription(this.discription);
    roleForm.setActive(active);
    roleForm.setUserAssociated(userAssociated);
    roleForm.setDefaultRole(defaultRole);
    if(priority != null)
    roleForm.setPriority(priority.toString());
    if(seriesId != null)
    {
    roleForm.setSeriesId(seriesId.toString());
    }
    List<ActivityForm> activityList = new ArrayList<ActivityForm>();
    if (activities != null && activities.size() > 0)
    {
      for (Activity activity : activities)
      {
        activityList.add(activity.getActivityForm());
      }
    }
    roleForm.setActivities(activityList);
    return roleForm;
  }

}
