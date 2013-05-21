package cricket.hibernate.bf.team;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import common.util.DateUtil;

import cricket.hibernate.bf.player.Player;
import cricket.struts.actionforms.team.PlayerForm;

public class UserTeamPlayers implements Serializable
{

  private Long id;

  private Date startDate;

  private Date endDate;

  private Long score;

  private boolean active;

  private Player player;

  private UserTeam userTeam;

  private long playerId;

  private long userTeamId;

  private Long matches;

  private Long runs;

  private Long wickets;

  private Long catches;

  private boolean captain;

  private Long centuries;

  private Long halfCenturies;

  private Long fours;

  private Long sixers;
  
  private String coreTeamName;


  public boolean isCaptain()
  {
  return captain;
  }

  public void setCaptain(boolean captain)
  {
  this.captain = captain;
  }

  public Long getId()
  {
  return id;
  }

  public void setId(Long id)
  {
  this.id = id;
  }

  public Date getStartDate()
  {
  return startDate;
  }

  public void setStartDate(Date startDate)
  {
  this.startDate = startDate;
  }

  public Date getEndDate()
  {
  return endDate;
  }

  public void setEndDate(Date endDate)
  {
  this.endDate = endDate;
  }

  public Player getPlayer()
  {
  return player;
  }

  public void setPlayer(Player player)
  {
  this.player = player;
  }

  public Long getScore()
  {
  return score;
  }

  public void setScore(Long score)
  {
  this.score = score;
  }

  public boolean isActive()
  {
  return active;
  }

  public void setActive(boolean active)
  {
  this.active = active;
  }

  public UserTeam getUserTeam()
  {
  return userTeam;
  }

  public void setUserTeam(UserTeam userTeam)
  {
  this.userTeam = userTeam;
  }

  public long getPlayerId()
  {
  return playerId;
  }

  public void setPlayerId(long playerId)
  {
  this.playerId = playerId;
  }

  public long getUserTeamId()
  {
  return userTeamId;
  }

  public void setUserTeamId(long userTeamId)
  {
  this.userTeamId = userTeamId;
  }

  public Long getRuns()
  {
  return runs;
  }

  public void setRuns(Long runs)
  {
  this.runs = runs;
  }

  public Long getWickets()
  {
  return wickets;
  }

  public void setWickets(Long wickets)
  {
  this.wickets = wickets;
  }

  public Long getCatches()
  {
  return catches;
  }

  public void setCatches(Long catches)
  {
  this.catches = catches;
  }

  public PlayerForm getActionForm()
  {
  PlayerForm form = new PlayerForm();
  if (player != null)
  {
    form.setId(player.getId());
    form.setName(player.getName());
    form.setSkill(player.getSkill());
    form.setCountry(player.getCountry().getCountry());
    form.setCountry(player.getCountry().getCountry());
    if(coreTeamName != null)
    form.setCoreTeamName(coreTeamName);
  }
  form.setActive(active);
  if (active)
  {
    form.setActiveString("Yes");
  }
  else
  {
    form.setActiveString("No");
  }
  if (startDate != null)
  {
    form.setStartDate(DateUtil.getStringFromDate(this.startDate, "dd/MM/yyyy"));
  }
  if (endDate != null)
  {
    form.setStartDate(DateUtil.getStringFromDate(this.endDate, "dd/MM/yyyy"));
  }
  if (score != null)
  {
    form.setScore(score.toString());
  }
  if (runs != null)
  {
    form.setRuns(runs.toString());
  }
  if (catches != null)
  {
    form.setCatches(catches.toString());
  }
  if (wickets != null)
  {
    form.setWickets(wickets.toString());
  }

  if (fours != null)
  {
    form.setFours(fours.toString());
  }
  if (sixers != null)
  {
    form.setSixers(sixers.toString());
  }
  if (centuries != null)
  {
    form.setCenturies(centuries.toString());
  }
  if (halfCenturies != null)
  {
    form.setHalfCenturies(halfCenturies.toString());
  }
  if (captain)
  {
    form.setCaptain(captain);
  }

  if (captain)
  {
    form.setCaptainString("Yes");
  }
  else
  {
    form.setCaptainString("No");
  }
  form.setTmpPlayerId(id);
  return form;
  }

  public Long getCenturies()
  {
  return centuries;
  }

  public void setCenturies(Long centuries)
  {
  this.centuries = centuries;
  }

  public Long getHalfCenturies()
  {
  return halfCenturies;
  }

  public void setHalfCenturies(Long halfCenturies)
  {
  this.halfCenturies = halfCenturies;
  }

  public Long getFours()
  {
  return fours;
  }

  public void setFours(Long fours)
  {
  this.fours = fours;
  }

  public Long getSixers()
  {
  return sixers;
  }

  public void setSixers(Long sixers)
  {
  this.sixers = sixers;
  }

  public Long getMatches()
  {
  return matches;
  }

  public void setMatches(Long matches)
  {
  this.matches = matches;
  }

  public String getCoreTeamName()
  {
  return coreTeamName;
  }

  public void setCoreTeamName(String coreTeamName)
  {
  this.coreTeamName = coreTeamName;
  }

  public UserTeamPlayers clone()
  {
  UserTeamPlayers teamPlayer = new UserTeamPlayers();
  teamPlayer.setCaptain(captain);
  teamPlayer.setStartDate(startDate);
  teamPlayer.setPlayerId(playerId);
  teamPlayer.setUserTeamId(userTeamId);
  teamPlayer.setCatches(catches);
  teamPlayer.setRuns(runs);
  teamPlayer.setWickets(wickets);
  teamPlayer.setScore(score);
  teamPlayer.setFours(fours);
  teamPlayer.setSixers(sixers);
  teamPlayer.setHalfCenturies(halfCenturies);
  teamPlayer.setCenturies(centuries);
  teamPlayer.setCoreTeamName(coreTeamName);
  return teamPlayer;
  }

  public String toString()
  {
  return ToStringBuilder.reflectionToString(this);
  }

}
