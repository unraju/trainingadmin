package cricket.hibernate.bf.player;

import java.io.Serializable;
import java.util.Date;

import common.util.DateUtil;

import cricket.struts.actionforms.team.PlayerSubstitutionForm;


public class PlayerSubstitution implements Serializable
{

  private Long id;

  private Date date;

  private Player oldPlayer;

  private Player newPlayer;

  private String substitionType;

  private String oldPlayerName;

  private String newPlayerName;

  private String status;

  private Long pointsDeducted;

  private Long userTeamId;
  
  private Long seriesId;

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

  public Player getOldPlayer()
  {
    return oldPlayer;
  }

  public void setOldPlayer(Player oldPlayer)
  {
    this.oldPlayer = oldPlayer;
  }

  public Player getNewPlayer()
  {
    return newPlayer;
  }

  public void setNewPlayer(Player newPlayer)
  {
    this.newPlayer = newPlayer;
  }

  public String getOldPlayerName()
  {
    return oldPlayerName;
  }

  public void setOldPlayerName(String oldPlayerName)
  {
    this.oldPlayerName = oldPlayerName;
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
  
  public Long getSeriesId()
  {
  return seriesId;
  }

  public void setSeriesId(Long seriesId)
  {
  this.seriesId = seriesId;
  }

  public PlayerSubstitutionForm getActionForm()
  {
  PlayerSubstitutionForm form =new PlayerSubstitutionForm();
  form.setNewPlayerName(newPlayerName);
  form.setOldPlayerName(oldPlayerName);
  form.setPointsDeducted(pointsDeducted);
  form.setSubstitionType(substitionType);
  form.setDate(DateUtil.getDateInGMT0530Zone(date));
  form.setStatus(status);
  return form;
  }

}
