package cricket.hibernate.bf.player;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

import cricket.hibernate.bf.common.SeriesType;
import cricket.struts.actionforms.player.PlayerScoresForm;

public class PlayerScores implements Serializable
{
  private Long id;

  private Long seriesTypeId;
  
  private SeriesType seriesType;

  private Player player;

  private Long matches;

  private Long runs;

  private Long wickets;

  private Long catches;

  private Long centuries;

  private Long halfCenturies;

  public PlayerScores()
  {
  super();
  matches = 0L;
  // id=0L;
  // runs = 0L;
  // wickets = 0L;
  // catches = 0L;
  // centuries = 0L;
  // halfCenturies = 0L;
  }

  public SeriesType getSeriesType()
  {
  return seriesType;
  }

  public void setSeriesType(SeriesType seriesType)
  {
  this.seriesType = seriesType;
  }

  public Long getId()
  {
  return id;
  }

  public void setId(Long id)
  {
  this.id = id;
  }

  public Long getMatches()
  {
  return matches;
  }

  public void setMatches(Long matches)
  {
  this.matches = matches;
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

  public Long getSeriesTypeId()
  {
  return seriesTypeId;
  }

  public void setSeriesTypeId(Long seriesTypeId)
  {
  this.seriesTypeId = seriesTypeId;
  }

  public void setHalfCenturies(Long halfCenturies)
  {
  this.halfCenturies = halfCenturies;
  }

  public Player getPlayer()
  {
  return player;
  }

  public void setPlayer(Player player)
  {
  this.player = player;
  }

  public PlayerScoresForm getActionForm()
  {
  PlayerScoresForm form = new PlayerScoresForm();
  form.setId(id);
  if (matches != null) form.setMatches(matches.toString());
  if (runs != null) form.setRuns(runs.toString());
  if (wickets != null) form.setWickets(wickets.toString());
  if (centuries != null) form.setCenturies(centuries.toString());
  if (halfCenturies != null) form.setHalfCenturies(halfCenturies.toString());
  form.setSeriesTypeId(seriesTypeId);
  form.setSeriesTypeName(seriesType.getName());
  return form;
  }

  public String toString()
  {
  return ToStringBuilder.reflectionToString(this);
  }

}
