package cricket.struts.actionforms.player;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.struts.action.ActionForm;

public class PlayerScoresForm extends ActionForm implements Serializable
{

  private Long id;

  private String matches = "";

  private String runs = "";

  private String balls = "";

  private String wickets = "";

  private String catches = "";

  private String score = "";

  private String seriesTypeName;

  private Long seriesTypeId;

  private String strikRate;

  private String centuries = "";

  private String halfCenturies = "";

  public Long getId()
  {
  return id;
  }

  public void setId(Long id)
  {
  this.id = id;
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

  public String getSeriesTypeName()
  {
  return seriesTypeName;
  }

  public void setSeriesTypeName(String seriesTypeName)
  {
  this.seriesTypeName = seriesTypeName;
  }

  public Long getSeriesTypeId()
  {
  return seriesTypeId;
  }

  public void setSeriesTypeId(Long seriesTypeId)
  {
  this.seriesTypeId = seriesTypeId;
  }

  public String getStrikRate()
  {
  return strikRate;
  }

  public void setStrikRate(String strikRate)
  {
  this.strikRate = strikRate;
  }

  public String getMatches()
  {
  return matches;
  }

  public void setMatches(String matches)
  {
  this.matches = matches;
  }

  public String getCenturies()
  {
  return centuries;
  }

  public void setCenturies(String centuries)
  {
  this.centuries = centuries;
  }

  public String getHalfCenturies()
  {
  return halfCenturies;
  }

  public void setHalfCenturies(String halfCenturies)
  {
  this.halfCenturies = halfCenturies;
  }

  public String toString()
  {
  return ToStringBuilder.reflectionToString(this);
  }

}
