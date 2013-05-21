package cricket.hibernate.bf.team;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import cricket.hibernate.bf.common.Country;
import cricket.hibernate.bf.common.Series;
import cricket.hibernate.bf.common.TeamGroup;
import cricket.struts.actionforms.team.CoreTeamForm;
import cricket.struts.actionforms.team.PlayerForm;

public class CoreTeam implements Serializable
{

  private Long id;

  private String name;
 
  private Date createdDate;

  private String city;

  private Country country;

  private String owner;

  private boolean active;

  private Collection<CoreTeamPlayers> players;

  private String code;

  private TeamGroup teamGroup;

  private Series series;

  private Long playersCount;

  public Series getSeries()
  {
  return series;
  }

  public void setSeries(Series series)
  {
  this.series = series;
  }

  public TeamGroup getTeamGroup()
  {
  return teamGroup;
  }

  public void setTeamGroup(TeamGroup teamGroup)
  {
  this.teamGroup = teamGroup;
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

  public Date getCreatedDate()
  {
  return createdDate;
  }

  public void setCreatedDate(Date createdDate)
  {
  this.createdDate = createdDate;
  }

  public String getCity()
  {
  return city;
  }

  public void setCity(String city)
  {
  this.city = city;
  }

  public Country getCountry()
  {
  return country;
  }

  public void setCountry(Country country)
  {
  this.country = country;
  }

  public String getOwner()
  {
  return owner;
  }

  public void setOwner(String owner)
  {
  this.owner = owner;
  }

  public boolean isActive()
  {
  return active;
  }

  public void setActive(boolean active)
  {
  this.active = active;
  }

  public Collection<CoreTeamPlayers> getPlayers()
  {
  return players;
  }

  public void setPlayers(Collection<CoreTeamPlayers> players)
  {
  this.players = players;
  }

  public String getCode()
  {
  return code;
  }

  public void setCode(String code)
  {
  this.code = code;
  }

  public Long getPlayersCount()
  {
  return playersCount;
  }

  public void setPlayersCount(Long playersCount)
  {
  this.playersCount = playersCount;
  }

  public CoreTeamForm getActionForm()
  {
  CoreTeamForm coreTeamForm = new CoreTeamForm();
  coreTeamForm.setId(id);
  coreTeamForm.setName(name);
  coreTeamForm.setCode(code);
  coreTeamForm.setCountry(country.getCountry());
  coreTeamForm.setCountryId(country.getId().toString());
  coreTeamForm.setCity(city);
  coreTeamForm.setActive(active);
  coreTeamForm.setOwner(owner);
  if(playersCount != null)
  coreTeamForm.setPlayersCount(playersCount.toString());
  List<PlayerForm> list = new ArrayList<PlayerForm>();
  if (players != null)
  {
    for (CoreTeamPlayers player : players)
    {
      if (player != null && player.getId() != null)
      {
        list.add(player.getActionForm());
      }
    }
  }
  Collections.sort(list, new CoreTeamPlayerScoreComparator());
  coreTeamForm.setPlayers(list);
  if (series != null)
  {
    coreTeamForm.setSeriesName(series.getName());
    coreTeamForm.setSeriesId(series.getId().toString());
  }
  if (teamGroup != null)
  {
    coreTeamForm.setGroupName(teamGroup.getGroupName());
    coreTeamForm.setGroupId(teamGroup.getId().toString());
  }
  return coreTeamForm;
  }

}
