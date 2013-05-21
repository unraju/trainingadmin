package common.struts.actionforms.user;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import common.util.Utility;

public class ActivityForm extends ActionForm
{

  private Long id;

  private String activity;

  private String activityUrl;

  private Long priority;

  private boolean checked;

  private boolean active;

  public Long getId()
  {

  return id;
  }

  public void setId(Long id)
  {

  this.id = id;
  }

  public String getActivity()
  {

  return activity;
  }

  public void setActivity(String activity)
  {

  this.activity = activity;
  }

  public String getActivityUrl()
  {

  return activityUrl;
  }

  public void setActivityUrl(String activityUrl)
  {

  this.activityUrl = activityUrl;
  }

  public Long getPriority()
  {

  return priority;
  }

  public void setPriority(Long priority)
  {

  this.priority = priority;
  }

  public boolean isChecked()
  {
  return checked;
  }

  public void setChecked(boolean checked)
  {
  this.checked = checked;
  }

  public boolean isActive()
  {
  return active;
  }

  public void setActive(boolean active)
  {
  this.active = active;
  }

  public String getActiveString()
  {
  return Utility.convertBooleanToString(active);
  }

  @Override
  public String toString()
  {
  // TODO Auto-generated method stub
  return ReflectionToStringBuilder.toString(this);
  }

  @Override
  public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
  {
  ActionErrors errors = new ActionErrors();
  String operation = request.getParameter("operation");
  String[] runtimeValues = new String[3];
  // Requestor validations
  if (operation != null && operation.equals("Save"))
  {

    if (activity == null || activity.trim().equals(""))
    {
      runtimeValues[0] = "Activity";
      errors.add("activity", new ActionMessage("errors.required", runtimeValues[0]));
    }

    if (activityUrl == null || activityUrl.trim().startsWith("Please"))
    {
      runtimeValues[1] = "Activity Url";
      errors.add("activityUrl", new ActionMessage("errors.required", runtimeValues[1]));
    }

  }
  return errors;
  }

}
