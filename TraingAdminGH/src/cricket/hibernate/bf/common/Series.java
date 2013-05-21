package cricket.hibernate.bf.common;

import java.io.Serializable;
import java.util.Date;

import common.util.Constants;
import common.util.DateUtil;

import cricket.struts.actionforms.common.SeriesForm;

public class Series implements Serializable
{

  private Long id;

  private String name;

  private Country country;

  private String venue;

  private Date startDate;

  private Date endDate;
  
  private SeriesType seriesType;

  private boolean active;
  
  private Long matches;
  
  private Long playerInSquad;

  public Country getCountry()
  {
  return country;
  }

  public void setCountry(Country country)
  {
  this.country = country;
  }

  public boolean isActive()
  {
  return active;
  }

  public void setActive(boolean active)
  {
  this.active = active;
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

  public String getVenue()
  {
  return venue;
  }

  public void setVenue(String venue)
  {
  this.venue = venue;
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
  

  public SeriesType getSeriesType()
  {
    return seriesType;
  }

  public void setSeriesType(SeriesType seriesType)
  {
    this.seriesType = seriesType;
  }

  public Long getMatches()
  {
  return matches;
  }

  public void setMatches(Long matches)
  {
  this.matches = matches;
  }

  public Long getPlayerInSquad()
  {
  return playerInSquad;
  }

  public void setPlayerInSquad(Long playerInSquad)
  {
  this.playerInSquad = playerInSquad;
  }

  public SeriesForm getActionForm()
  {
  // TODO Auto-generated method stub
  SeriesForm form = new SeriesForm();
  form.setId(id);
  form.setName(name);
  form.setSeriesType(seriesType.getName());
  form.setStartDate(DateUtil.getStringFromDate(this.startDate, Constants.DATE_TEME_AM__PM_SHORT));
  form.setEndDate(DateUtil.getStringFromDate(this.endDate, Constants.DATE_TEME_AM__PM_SHORT));
  form.setVenue(venue);
  form.setMatches(matches.intValue());
  form.setPlayerInSquad(playerInSquad.intValue());
  form.setActive(active);
  return form;
  }

}
