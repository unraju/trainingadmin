package cricket.struts.actionforms.team;

import org.apache.struts.action.ActionForm;

public class ScoreReportForm extends ActionForm
{

  private Long id;

  private String date;

  private String status;

  private String discription;

  private String userName = "";

  public Long getId()
  {
  return id;
  }

  public void setId(Long id)
  {
  this.id = id;
  }

  public String getDate()
  {
  return date;
  }

  public void setDate(String date)
  {
  this.date = date;
  }

  public String getStatus()
  {
  return status;
  }

  public void setStatus(String status)
  {
  this.status = status;
  }

  public String getDiscription()
  {
  return discription;
  }

  public void setDiscription(String discription)
  {
  this.discription = discription;
  }

  public String getUserName()
  {
  return userName;
  }

  public void setUserName(String userName)
  {
  this.userName = userName;
  }

}
