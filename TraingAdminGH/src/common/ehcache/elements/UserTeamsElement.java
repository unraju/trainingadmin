package common.ehcache.elements;

import java.io.Serializable;
import java.util.List;

import cricket.struts.actionforms.team.UserTeamForm;

public class UserTeamsElement implements Serializable
{
  public static final String USER_TEAMS = "USER_TEAMS";
  
  public List<UserTeamForm> userTeamForms;

  public UserTeamsElement(List<UserTeamForm> userTeamForms)
  {
    super();
    this.userTeamForms = userTeamForms;
  }

  public List<UserTeamForm> getUserTeamForms()
  {
    return userTeamForms;
  }
  
  
 }
