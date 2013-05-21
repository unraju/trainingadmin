package common.ehcache.elements;

import java.io.Serializable;
import java.util.List;

import cricket.struts.actionforms.team.CurrentMatchSheduleForm;

public class LiveMatchScheduleElement implements Serializable
{
  public static final String LIVE_MATCH_SCHEDULE = "LIVE_MATCH_SCHEDULE";

  public List<CurrentMatchSheduleForm> currentMatchSheduleForms;

  public LiveMatchScheduleElement(List<CurrentMatchSheduleForm> currentMatchSheduleForms)
  {
    super();
    this.currentMatchSheduleForms = currentMatchSheduleForms;
  }

  public List<CurrentMatchSheduleForm> getCurrentMatchSheduleForms()
  {
    return currentMatchSheduleForms;
  }

}
