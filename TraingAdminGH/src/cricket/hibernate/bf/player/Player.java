package cricket.hibernate.bf.player;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

import cricket.hibernate.bf.common.Country;
import cricket.struts.actionforms.player.PlayerScoresForm;
import cricket.struts.actionforms.team.PlayerForm;

public class Player implements Serializable
{
  private Long id;

  private String name;

  private String countryString;

  private Country country;

  private boolean active;

  private String skill;

  private Long score;

  private Date startDate;

  private Date endDate;

  private boolean selected;

  private Long matches;

  private Long runs;

  private Long wickets;

  private Long catches;

  private Long centuries;

  private Long halfCenturies;
  
  private List<PlayerScores> playerScores;
  
  
  public Player()
  {
  super();
  matches = 0L;
  runs = 0L;
  wickets = 0L;
  catches = 0L;
  centuries = 0L;
  halfCenturies = 0L;
  }

  public Long getScore()
  {
  return score;
  }

  public void setScore(Long score)
  {
  this.score = score;
  }

  public Long getId()
  {
  return id;
  }

  public void setId(Long id)
  {
  this.id = id;
  }

  public String getName()
  {
  return name;
  }

  public void setName(String name)
  {
  this.name = name;
  }

  public boolean isActive()
  {
  return active;
  }

  public void setActive(boolean active)
  {
  this.active = active;
  }

  public String getSkill()
  {
  return skill;
  }

  public void setSkill(String skill)
  {
  this.skill = skill;
  }

  public Date getStartDate()
  {
  return startDate;
  }

  public void setStartDate(Date startDate)
  {
  this.startDate = startDate;
  }

  public String getCountryString()
  {
  return countryString;
  }

  public void setCountryString(String countryString)
  {
  this.countryString = countryString;
  }

  public Country getCountry()
  {
  return country;
  }

  public void setCountry(Country country)
  {
  this.country = country;
  }

  public Date getEndDate()
  {
  return endDate;
  }

  public void setEndDate(Date endDate)
  {
  this.endDate = endDate;
  }

  public boolean isSelected()
  {
  return selected;
  }

  public void setSelected(boolean selected)
  {
  this.selected = selected;
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

  public void setHalfCenturies(Long halfCenturies)
  {
  this.halfCenturies = halfCenturies;
  }

  public List<PlayerScores> getPlayerScores()
  {
  return playerScores;
  }

  public void setPlayerScores(List<PlayerScores> playerScores)
  {
  this.playerScores = playerScores;
  }

  public PlayerForm getActionForm()
  {
  PlayerForm form = new PlayerForm();
  form.setId(id);
  form.setChangedPlayerId(id);
  form.setName(name);
  form.setActive(active);
  form.setCountry(country.getId().toString());
  form.setCountryName(country.getCountry());
  form.setCountryShortName(country.getShortName());
  form.setSkill(skill);
  form.setSelected(selected);
  form.setMatches(matches.toString());
  form.setRuns(runs.toString());
  form.setWickets(wickets.toString());
  form.setCenturies(centuries.toString());
  form.setHalfCenturies(halfCenturies.toString());
  //form.setPlayerScores(getPlayerScoreForms());
  if (active)
  {
    form.setActiveString("Yes");
  }
  else
  {
    form.setActiveString("No");
  }
  return form;
  }

  private List<PlayerScoresForm> getPlayerScoreForms()
  {
  List<PlayerScoresForm> playerScoresForms = new ArrayList<PlayerScoresForm>();
   for(PlayerScores playerScore :playerScores)
  {
    if (playerScore != null)
    {
      playerScoresForms.add(playerScore.getActionForm());
    }
  }
  return playerScoresForms;
  }

  public String toString()
  {
  return ToStringBuilder.reflectionToString(this);
  }

  public PlayerForm getActionFormForManagePlayer()
  {
  PlayerForm form = new PlayerForm();
  form.setId(id);
  form.setChangedPlayerId(id);
  form.setName(name);
  form.setActive(active);
  form.setCountry(country.getId().toString());
  form.setCountryName(country.getCountry());
  form.setCountryShortName(country.getShortName());
  form.setSkill(skill);
  form.setSelected(selected);
  form.setMatches(matches.toString());
  form.setRuns(runs.toString());
  form.setWickets(wickets.toString());
  form.setCenturies(centuries.toString());
  form.setHalfCenturies(halfCenturies.toString());
  form.setPlayerScores(getPlayerScoreForms());
  if (active)
  {
    form.setActiveString("Yes");
  }
  else
  {
    form.setActiveString("No");
  }
  return form;
  }

}
