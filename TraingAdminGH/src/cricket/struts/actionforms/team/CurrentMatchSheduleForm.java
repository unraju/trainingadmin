package cricket.struts.actionforms.team;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.struts.action.ActionForm;

public class CurrentMatchSheduleForm extends ActionForm
{

  private Long id;

  private Long firstTeamId;

  private Long secondTeamId;

  private String date;

  private String venue;

  private String matchName;

  private String firstTeamName;

  private String secondTeamName;

  private String seriesId;
  
  private boolean scoresGenerated;
  
  private String matchResult="";
  
  private String matchShortName;

  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
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

  public String getSeriesId()
  {
    return seriesId;
  }

  public void setSeriesId(String seriesId)
  {
    this.seriesId = seriesId;
  }

  public boolean isScoresGenerated()
  {
  return scoresGenerated;
  }

  public void setScoresGenerated(boolean scoresGenerated)
  {
  this.scoresGenerated = scoresGenerated;
  }

  public String getMatchResult()
  {
  return matchResult;
  }

  public void setMatchResult(String matchResult)
  {
  this.matchResult = matchResult;
  }

  public String getMatchShortName()
  {
  return matchShortName;
  }

  public void setMatchShortName(String matchShortName)
  {
  this.matchShortName = matchShortName;
  }

  public String toString()
  {
    return ToStringBuilder.reflectionToString(this);
  }
}
