package cricket.struts.actionforms.team;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.struts.action.ActionForm;

public class PlayerMatchScoresForm extends ActionForm
{

  private Long id;

  private Date date;

  private String playerName;

  private String matchName;

  private Long playerId;

  private Long matchId;
  
  private String order = "";

  private String runs = "";

  private String balls = "";

  private String wickets = "";

  private String catches = "";

  private String score = "";
  
  private String fours = "";

  private String sixers = "";
  
  private String seriesName;

  private Long seriesId;

  private String strikRate="";
  
  private String dateString;
  
  
  public String getSeriesName()
  {
  return seriesName;
  }

  public void setSeriesName(String seriesName)
  {
  this.seriesName = seriesName;
  }

  public Long getId()
  {
  return id;
  }

  public void setId(Long id)
  {
  this.id = id;
  }

  public Long getPlayerId()
  {
  return playerId;
  }

  public void setPlayerId(Long playerId)
  {
  this.playerId = playerId;
  }

  public Long getMatchId()
  {
  return matchId;
  }

  public void setMatchId(Long matchId)
  {
  this.matchId = matchId;
  }

  public Date getDate()
  {
  return date;
  }

  public void setDate(Date date)
  {
  this.date = date;
  }

  public String getPlayerName()
  {
  return playerName;
  }

  public void setPlayerName(String playerName)
  {
  this.playerName = playerName;
  }

  public String getMatchName()
  {
  return matchName;
  }

  public void setMatchName(String matchName)
  {
  this.matchName = matchName;
  }

  public String getRuns()
  {
  return runs;
  }

  public void setRuns(String runs)
  {
  this.runs = runs;
  }

  public String getBalls()
  {
  return balls;
  }

  public void setBalls(String balls)
  {
  this.balls = balls;
  }

  public String getWickets()
  {
  return wickets;
  }

  public void setWickets(String wickets)
  {
  this.wickets = wickets;
  }

  public String getCatches()
  {
  return catches;
  }

  public void setCatches(String catches)
  {
  this.catches = catches;
  }

  public String getScore()
  {
  return score;
  }

  public void setScore(String score)
  {
  this.score = score;
  }

  public Long getSeriesId()
  {
  return seriesId;
  }

  public void setSeriesId(Long seriesId)
  {
  this.seriesId = seriesId;
  }

  public String getStrikRate()
  {
  return strikRate;
  }

  public void setStrikRate(String strikRate)
  {
  this.strikRate = strikRate;
  }

  public String toString()
  {
  return ToStringBuilder.reflectionToString(this);
  }

  public String getDateString()
  {
  return dateString;
  }

  public void setDateString(String dateString)
  {
  this.dateString = dateString;
  }

  public String getFours()
  {
  return fours;
  }

  public void setFours(String fours)
  {
  this.fours = fours;
  }

  public String getSixers()
  {
  return sixers;
  }

  public void setSixers(String sixers)
  {
  this.sixers = sixers;
  }

  public String getOrder()
  {
  return order;
  }

  public void setOrder(String order)
  {
  this.order = order;
  }

}
