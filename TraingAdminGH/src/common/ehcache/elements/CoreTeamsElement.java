package common.ehcache.elements;

import java.io.Serializable;
import java.util.List;

import cricket.struts.actionforms.team.CoreTeamForm;

public class CoreTeamsElement implements Serializable
{
  public static final String SEARCHED_CORE_TEAMS = "SEARCHED_CORE_TEAMS";
  
  public List<CoreTeamForm> coreteamForms;

  public CoreTeamsElement(List<CoreTeamForm> coreteamForms)
  {
    super();
    this.coreteamForms = coreteamForms;
  }

  public List<CoreTeamForm> getCoreteamForms()
  {
    return coreteamForms;
  }
  
  
 }
