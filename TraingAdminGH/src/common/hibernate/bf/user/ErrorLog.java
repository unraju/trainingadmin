package common.hibernate.bf.user;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class ErrorLog implements Serializable
{

  private Long id;

  private String userName;

  private String errorDescription;

  private Date time;

  private String stackTrace;

  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public String getUserName()
  {
    return userName;
  }

  public void setUserName(String userName)
  {
    this.userName = userName;
  }

  public String getErrorDescription()
  {
    return errorDescription;
  }

  public void setErrorDescription(String errorDescription)
  {
    this.errorDescription = errorDescription;
  }

  public Date getTime()
  {
    return time;
  }

  public void setTime(Date time)
  {
    this.time = time;
  }

  public String getStackTrace()
  {
    return stackTrace;
  }

  public void setStackTrace(String stackTrace)
  {
    this.stackTrace = stackTrace;
  }

  @Override
  public String toString()
  {
    return ReflectionToStringBuilder.toString(this);
  }

  /*public ActivityForm getActivityForm()
  {
    ActivityForm activityForm = new ActivityForm();
    activityForm.setId(id);
    activityForm.setActivity(activity);
    activityForm.setActivityUrl(activityUrl);
    activityForm.setPriority(priority);
    activityForm.setActive(active);
    return activityForm;
  }*/

}
