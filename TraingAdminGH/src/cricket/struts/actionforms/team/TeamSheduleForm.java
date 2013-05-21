package cricket.struts.actionforms.team;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.struts.action.ActionForm;

public class TeamSheduleForm extends ActionForm
{

  private Long id;

  private CoreTeamForm firstTeamForm;

  private CoreTeamForm secondTeamForm;

  private Long firstTeamId;

  private Long secondTeamId;

  private String date;

  private boolean scoreGenerated;

  private String displayDate;

  private String time;

  private String venue;

  private String matchName;

  private String firstTeamName;

  private String secondTeamName;

  private String seriesId;
  
  private String firstTeamScore = "";

  private String secondTeamScore="";

  private String matchResult="";

  private String firstTeamExtras="0";

  private String secondTeamExtras="0";
  
  private java.util.Date dateObject;
  
  private String manOfMatch = "";
  
  public String getSeriesId()
  {
  return seriesId;
  }

  public void setSeriesId(String seriesId)
  {
  this.seriesId = seriesId;
  }

  public String getSeriesName()
  {
  return SeriesName;
  }

  public void setSeriesName(String seriesName)
  {
  SeriesName = seriesName;
  }

  private String SeriesName;

  public Long getId()
  {
  return id;
  }

  public void setId(Long id)
  {
  this.id = id;
  }

  public CoreTeamForm getFirstTeamForm()
  {
  return firstTeamForm;
  }

  public void setFirstTeamForm(CoreTeamForm firstTeamForm)
  {
  this.firstTeamForm = firstTeamForm;
  }

  public CoreTeamForm getSecondTeamForm()
  {
  return secondTeamForm;
  }

  public void setSecondTeamForm(CoreTeamForm secondTeamForm)
  {
  this.secondTeamForm = secondTeamForm;
  }

  public String getDate()
  {
  return date;
  }

  public void setDate(String date)
  {
  this.date = date;
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

  public Long getFirstTeamId()
  {
  return firstTeamId;
  }

  public void setFirstTeamId(Long firstTeamId)
  {
  this.firstTeamId = firstTeamId;
  }

  public Long getSecondTeamId()
  {
  return secondTeamId;
  }

  public void setSecondTeamId(Long secondTeamId)
  {
  this.secondTeamId = secondTeamId;
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

  public String getDisplayDate()
  {
  return displayDate;
  }

  public void setDisplayDate(String displayDate)
  {
  this.displayDate = displayDate;
  }

  public String getTime()
  {
  return time;
  }

  public void setTime(String time)
  {
  this.time = time;
  }

  public boolean isScoreGenerated()
  {
   if (matchResult != null && !matchResult.trim().equals("") )
  {
    return true;
  }
  else
  {
    return false;
  }

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

  public String getFirstTeamExtras()
  {
  return firstTeamExtras;
  }

  public void setFirstTeamExtras(String firstTeamExtras)
  {
  this.firstTeamExtras = firstTeamExtras;
  }

  public String getSecondTeamExtras()
  {
  return secondTeamExtras;
  }

  public void setSecondTeamExtras(String secondTeamExtras)
  {
  this.secondTeamExtras = secondTeamExtras;
  }

  public void setScoreGenerated(boolean scoreGenerated)
  {
  this.scoreGenerated = scoreGenerated;
  }

  public static List<TeamSheduleForm> getDummyTeamSheduleForms(List<TeamSheduleForm> teamSheduleForms, int matchCount)
  {
  int size = teamSheduleForms.size();
  int tempMatchCount = 1000+matchCount;
  size = tempMatchCount - size;
  for (int count = 1001; count <= size; count++)
  {
    TeamSheduleForm teamSheduleForm = new TeamSheduleForm();
    teamSheduleForm.setVenue("");
    teamSheduleForm.setMatchName("");
    teamSheduleForm.setDate("");
    teamSheduleForm.setId(Long.valueOf(count));
    teamSheduleForms.add(teamSheduleForm);
  }
  return teamSheduleForms;
  }


  public java.util.Date getDateObject()
  {
  return dateObject;
  }

  public void setDateObject(java.util.Date dateObject)
  {
  this.dateObject = dateObject;
  }

  public String getManOfMatch()
  {
  return manOfMatch;
  }

  public void setManOfMatch(String manOfMatch)
  {
  this.manOfMatch = manOfMatch;
  }

  public String toString()
  {
  return ToStringBuilder.reflectionToString(this);
  }
}
