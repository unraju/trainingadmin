package cricket.hibernate.bf.common;

import java.io.Serializable;
import java.util.List;

import cricket.hibernate.bf.team.CoreTeam;

public class TeamGroup implements Serializable
{
  private Long id;

  private String groupName;

  private String discription;

  private List<CoreTeam> teams;

  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public String getGroupName()
  {
    return groupName;
  }

  public void setGroupName(String groupName)
  {
    this.groupName = groupName;
  }

  public String getDiscription()
  {
    return discription;
  }

  public void setDiscription(String discription)
  {
    this.discription = discription;
  }

  public List<CoreTeam> getTeams()
  {
    return teams;
  }

  public void setTeams(List<CoreTeam> teams)
  {
    this.teams = teams;
  }

}
