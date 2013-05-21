package cricket.struts.actionforms.common;

import org.apache.struts.action.ActionForm;

public class SeriesForm extends ActionForm
{

  private Long id;

  private String name;

  private String seriesType;

  private String venue;

  private String startDate;

  private String endDate;

  private int matches;

  private int playerInSquad;
  
  private boolean active;

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

  public String getSeriesType()
  {
  return seriesType;
  }

  public void setSeriesType(String seriesType)
  {
  this.seriesType = seriesType;
  }

  public String getVenue()
  {
  return venue;
  }

  public void setVenue(String venue)
  {
  this.venue = venue;
  }

  public String getStartDate()
  {
  return startDate;
  }

  public void setStartDate(String startDate)
  {
  this.startDate = startDate;
  }

  public String getEndDate()
  {
  return endDate;
  }

  public void setEndDate(String endDate)
  {
  this.endDate = endDate;
  }

  public int getMatches()
  {
  return matches;
  }

  public void setMatches(int matches)
  {
  this.matches = matches;
  }

  public int getPlayerInSquad()
  {
  return playerInSquad;
  }

  public void setPlayerInSquad(int playerInSquad)
  {
  this.playerInSquad = playerInSquad;
  }

  public boolean isActive()
  {
  return active;
  }

  public void setActive(boolean active)
  {
  this.active = active;
  }

}
