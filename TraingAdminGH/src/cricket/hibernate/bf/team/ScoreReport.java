package cricket.hibernate.bf.team;

import java.io.Serializable;
import java.util.Date;

import common.util.Constants;
import common.util.DateUtil;

import cricket.struts.actionforms.team.ScoreReportForm;

public class ScoreReport implements Serializable
{

  private Long id;

  private Date date;

  private String status;

  private String discription;
  
  private String userName;
  
  private Long seriesId;

  public String getUserName()
  {
  return userName;
  }

  public void setUserName(String userName)
  {
  this.userName = userName;
  }

  public Long getId()
  {
  return id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public Date getDate()
  {
    return date;
  }

  public void setDate(Date date)
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
  
  public Long getSeriesId()
  {
  return seriesId;
  }

  public void setSeriesId(Long seriesId)
  {
  this.seriesId = seriesId;
  }

  public ScoreReportForm getActionForm()
  {
  ScoreReportForm reportForm = new ScoreReportForm();
  reportForm.setDate(DateUtil.getStringFromDate(DateUtil.getDateInGMT0530Zone(date),Constants.DATE_TEME_AM_FRM));
  reportForm.setDiscription(discription);
  reportForm.setStatus(status);
  if(userName != null)
  reportForm.setUserName(userName);
  return reportForm;
  }

}
