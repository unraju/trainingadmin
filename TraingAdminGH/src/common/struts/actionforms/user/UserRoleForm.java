package common.struts.actionforms.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import common.util.Utility;

public class UserRoleForm extends ActionForm
{

  private Long id;

  private String role;

  private boolean active;

  private String discription;

  private boolean checked;

  private List<ActivityForm> activities;

  private List<ActivityForm> allActivities;
  
  private boolean userAssociated;
  
  private boolean defaultRole;
  
  private String priority = "";
  
  private String seriesId = null;

  public Long getId()
  {

    return id;
  }

  public void setId(Long id)
  {

    this.id = id;
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

  public String getDiscription()
  {

    return discription;
  }

  public void setDiscription(String discription)
  {

    this.discription = discription;
  }

  public boolean isChecked()
  {
    return checked;
  }

  public void setChecked(boolean checked)
  {
    this.checked = checked;
  }

  public List<ActivityForm> getActivities()
  {
    return activities;
  }

  public void setActivities(List<ActivityForm> activities)
  {
    this.activities = activities;
  }

  public List<ActivityForm> getAllActivities()
  {
    return allActivities;
  }

  public void setAllActivities(List<ActivityForm> allActivities)
  {
    this.allActivities = allActivities;
  }

  public boolean isUserAssociated()
  {
    return userAssociated;
  }

  public void setUserAssociated(boolean userAssociated)
  {
    this.userAssociated = userAssociated;
  }

  public String getUserAssociatedString()
  {
    return Utility.convertBooleanToString(userAssociated);
  }

  public String getActive()
  {
    return Utility.convertBooleanToString(active);
  }
  
  public boolean isDefaultRole()
  {
    return defaultRole;
  }

  public void setDefaultRole(boolean defaultRole)
  {
    this.defaultRole = defaultRole;
  }

  public String getDefaultRoleString()
  {
    return Utility.convertBooleanToString(defaultRole);
  }
  
  public String getPriority()
  {
    return priority;
  }

  public void setPriority(String priority)
  {
    this.priority = priority;
  }

  public String getSeriesId()
  {
  return seriesId;
  }

  public void setSeriesId(String seriesId)
  {
  this.seriesId = seriesId;
  }

  @Override
  public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
  {
  ActionErrors errors = new ActionErrors();
  String operation = request.getParameter("operation");

  // Requestor validations
  String[] runtimeValues = new String[6];
  if (role == null || role.trim().equals(""))
  {
    runtimeValues[0] = "Role";
    errors.add("role", new ActionMessage("errors.required", runtimeValues[0]));
  }

  if (discription == null || discription.trim().startsWith("Please"))
  {
    runtimeValues[1] = "Discription";
    errors.add("discription", new ActionMessage("errors.required", runtimeValues[1]));
  }

  return errors;
  }
  
  public String getActivitiesAsString()
  {
    StringBuffer buffer = new StringBuffer();
    if (activities != null && activities.size() > 0)
    {
      for (ActivityForm role : activities)
      {
        buffer.append(role.getActivity());
        buffer.append(", ");
      }
    }
    return buffer.toString();
  }
}
