package cricket.struts.actionforms.team;

import java.util.Date;

import org.apache.struts.action.ActionForm;

import common.util.Constants;
import common.util.DateUtil;

public class PlayerSubstitutionForm extends ActionForm
{

  private Long id;

  private Date date;

  private String substitionType;

  private String oldPlayerName;

  private String newPlayerName;

  private String status;

  private Long pointsDeducted;

  private Long userTeamId;
  
  private String dateSTring;

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

  public String getOldPlayerName()
  {
  return oldPlayerName;
  }

  public void setOldPlayerName(String oldPlayerName)
  {
  this.oldPlayerName = oldPlayerName;
  }

  public String getDateSTring()
  {
  /*int minutes = date.getMinutes();
  minutes=minutes+330;
  date.setMinutes(minutes);*/
  //
  return DateUtil.getStringFromDate(date,Constants.DATE_TEME_AM_FRM);
  }

  public String getNewPlayerName()
  {
  return newPlayerName;
  }

  public void setNewPlayerName(String newPlayerName)
  {
  this.newPlayerName = newPlayerName;
  }

  public String getStatus()
  {
  return status;
  }

  public void setStatus(String status)
  {
  this.status = status;
  }

  public Long getPointsDeducted()
  {
  return pointsDeducted;
  }

  public void setPointsDeducted(Long pointsDeducted)
  {
  this.pointsDeducted = pointsDeducted;
  }

  public String getSubstitionType()
  {
  return substitionType;
  }

  public void setSubstitionType(String substitionType)
  {
  this.substitionType = substitionType;
  }

  public Long getUserTeamId()
  {
  return userTeamId;
  }

  public void setUserTeamId(Long userTeamId)
  {
  this.userTeamId = userTeamId;
  }

}
