package common.hibernate.bf.user;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import common.struts.actionforms.user.ActivityForm;

public class Activity implements Serializable
{

  private Long id;

  private String activity;

  private String activityUrl;

  private Long priority;

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

  public boolean isActive()
  {
    return active;
  }

  public void setActive(boolean active)
  {
    this.active = active;
  }

  @Override
  public String toString()
  {
    StringBuffer sb = new StringBuffer();
    sb.append(" activity ").append(activity);
    // This used to implementing toString method using Reflection. 
    return ReflectionToStringBuilder.toString(this); 
    //return sb.toString();
  }

  public ActivityForm getActivityForm()
  {
    ActivityForm activityForm = new ActivityForm();
    activityForm.setId(id);
    activityForm.setActivity(activity);
    activityForm.setActivityUrl(activityUrl);
    activityForm.setPriority(priority);
    activityForm.setActive(active);
    return activityForm;
  }

}
