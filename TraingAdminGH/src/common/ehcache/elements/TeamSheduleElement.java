package common.ehcache.elements;

import java.io.Serializable;
import java.util.List;

import cricket.struts.actionforms.team.TeamSheduleForm;

public class TeamSheduleElement implements Serializable
{
  public static final String TEAM_SHEDULE = "TEAM_SHEDULE";

  public List<TeamSheduleForm> sheduleForms;

  public TeamSheduleElement(List<TeamSheduleForm> sheduleForms)
  {
    super();
    this.sheduleForms = sheduleForms;
  }

  public List<TeamSheduleForm> getSheduleForms()
  {
    return sheduleForms;
  }

}
