package cricket.hibernate.bf.team;

import java.io.Serializable;
import java.util.Date;

import common.util.Constants;
import common.util.DateUtil;

import cricket.hibernate.bf.common.Series;
import cricket.struts.actionforms.team.CurrentMatchSheduleForm;
import cricket.struts.actionforms.team.TeamSheduleForm;

public class TeamShedule implements Serializable
{

  private Long id;

  private String matchName;
  
  private String matchShortName;

  private Date date;

  private CoreTeam firstTeam;

  private CoreTeam secondTeam;

  private String venue;

  private String firstTeamName;

  private String secondTeamName;

  private Series series;

  private String firstTeamScore = "";

  private String secondTeamScore = "";

  private String matchResult = "";
  
  private String manOfMatch = "";

  private Long firstTeamExtras = 0L;

  private Long secondTeamExtras = 0L;
  


  public TeamShedule()
  {
  super();

  }

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

  public CoreTeam getFirstTeam()
  {
  return firstTeam;
  }

  public void setFirstTeam(CoreTeam firstTeam)
  {
  this.firstTeam = firstTeam;
  }

  public CoreTeam getSecondTeam()
  {
  return secondTeam;
  }

  public void setSecondTeam(CoreTeam secondTeam)
  {
  this.secondTeam = secondTeam;
  }

  public String getVenue()
  {
  return venue;
  }

  public void setVenue(String venue)
  {
  this.venue = venue;
  }

  public String getMatchName()
  {
  return matchName;
  }

  public void setMatchName(String matchName)
  {
  this.matchName = matchName;
  }

  public String getFirstTeamName()
  {
  return firstTeamName;
  }

  public void setFirstTeamName(String firstTeamName)
  {
  this.firstTeamName = firstTeamName;
  }

  public String getSecondTeamName()
  {
  return secondTeamName;
  }

  public void setSecondTeamName(String secondTeamName)
  {
  this.secondTeamName = secondTeamName;
  }

  public String getMatchShortName()
  {
  return matchShortName;
  }

  public void setMatchShortName(String matchShortName)
  {
  this.matchShortName = matchShortName;
  }

  public TeamSheduleForm getActionForm()
  {
  TeamSheduleForm form = new TeamSheduleForm();
  form.setId(id);
  if (firstTeam != null)
  {
    form.setFirstTeamForm(firstTeam.getActionForm());
  }
  if (secondTeam != null)
  {
    form.setSecondTeamForm(secondTeam.getActionForm());
  }
  if (date != null)
  {
    form.setDate(DateUtil.getStringFromDate(this.date, Constants.DATE_FORMAT_DD_MMM_YYYY));
    form.setDisplayDate(DateUtil.getStringFromDate(this.date, Constants.DATE_TEME_AM_FRM));
    form.setTime(DateUtil.getStringFromDate(this.date, Constants.TIME_FRM_HH_MM));
  }
  form.setVenue(venue);
  form.setMatchName(matchName);
  form.setFirstTeamName(firstTeamName);
  form.setSecondTeamName(secondTeamName);

  if (firstTeamExtras != null) form.setFirstTeamExtras(firstTeamExtras.toString());
  if (secondTeamExtras != null) form.setSecondTeamExtras(secondTeamExtras.toString());
  if (firstTeamScore != null) form.setFirstTeamScore(firstTeamScore);
  if (secondTeamScore != null) form.setSecondTeamScore(secondTeamScore);
  if (matchResult != null) form.setMatchResult(matchResult);
  if (manOfMatch != null) form.setManOfMatch(manOfMatch);
  if (series != null)
  {
    form.setSeriesName(series.getName());
    form.setSeriesId(series.getId().toString());
  }
  return form;
  }

  public String getManOfMatch()
  {
  return manOfMatch;
  }

  public void setManOfMatch(String manOfMatch)
  {
  this.manOfMatch = manOfMatch;
  }

  public TeamSheduleForm getActionFormForView()
  {
    TeamSheduleForm form = new TeamSheduleForm();
    form.setId(id);

    if (date != null)
    {
      form.setDate(DateUtil.getStringFromDate(this.date, Constants.DATE_FORMAT_DD_MMM_YYYY));
      form.setDisplayDate(DateUtil.getStringFromDate(this.date, Constants.DATE_TEME_AM__PM_SHORT));
      form.setTime(DateUtil.getStringFromDate(this.date, Constants.TIME_FRM_HH_MM));
      form.setDateObject(date);
     /* Date now = DateUtil.getCurrentDateAsTimestampinGMT0530();
        if (now.before(series.getStartDate()))
      {
        form.setScoreGenerated(false);
      }*/
      
    }
    form.setVenue(venue);
    form.setMatchName(matchName);
    form.setFirstTeamName(firstTeamName);
    form.setSecondTeamName(secondTeamName);

    if(firstTeamExtras != null)
    form.setFirstTeamExtras(firstTeamExtras.toString());
    if(secondTeamExtras != null)
    form.setSecondTeamExtras(secondTeamExtras.toString());
    if(firstTeamScore != null)
    form.setFirstTeamScore(firstTeamScore);
    if(secondTeamScore != null)
    form.setSecondTeamScore(secondTeamScore);
    if(matchResult != null)
    form.setMatchResult(matchResult);
    if(manOfMatch != null)
      form.setManOfMatch(manOfMatch);
    if (series != null)
    {
      form.setSeriesName(series.getName());
      form.setSeriesId(series.getId().toString());
    }
    return form;
    }

  public String getFirstTeamScore()
  {
  return firstTeamScore;
  }

  public void setFirstTeamScore(String firstTeamScore)
  {
  this.firstTeamScore = firstTeamScore;
  }

  public String getSecondTeamScore()
  {
  return secondTeamScore;
  }

  public void setSecondTeamScore(String secondTeamScore)
  {
  this.secondTeamScore = secondTeamScore;
  }

  public String getMatchResult()
  {
  return matchResult;
  }

  public void setMatchResult(String matchResult)
  {
  this.matchResult = matchResult;
  }

  public Long getFirstTeamExtras()
  {
  return firstTeamExtras;
  }

  public void setFirstTeamExtras(Long firstTeamExtras)
  {
  this.firstTeamExtras = firstTeamExtras;
  }

  public Long getSecondTeamExtras()
  {
  return secondTeamExtras;
  }

  public void setSecondTeamExtras(Long secondTeamExtras)
  {
  this.secondTeamExtras = secondTeamExtras;
  }

  public Series getSeries()
  {
  return series;
  }

  public void setSeries(Series series)
  {
  this.series = series;
  }

  public CurrentMatchSheduleForm getCurrentTeamSheduleForm()
  {
    CurrentMatchSheduleForm form = new CurrentMatchSheduleForm();
    form.setId(id);

    if (date != null)
    {
      form.setDate(DateUtil.getStringFromDate(this.date, Constants.DATE_TEME_AM__PM_SHORT));
    }
    /*if(DateUtil.getCurrentDateAsTimestampinGMT0530().after(date))
    {
      form.setScoresGenerated(true);
    }*/
    
    if(matchResult != null && !matchResult.trim().equals(""))
    {
      form.setScoresGenerated(true);
    }
    form.setVenue(venue);
    form.setMatchName(matchName);
    form.setFirstTeamName(firstTeamName);
    form.setSecondTeamName(secondTeamName);
    form.setMatchResult(matchResult);
    form.setMatchShortName(matchShortName);
    if (series != null)
    {
      form.setSeriesId(series.getId().toString());
    }
    return form;
    }

}
