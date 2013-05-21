package cricket.struts.actionforms.common;

import org.apache.struts.action.ActionForm;

public class ConfigDataForm extends ActionForm
{

  private Long id;

  private Long coreTeamsCount;

  private Long coreTeamPlayersCount;

  private Long freeSubstututions;

  private String updateUserTeamCutOffDate;

  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public Long getCoreTeamsCount()
  {
    return coreTeamsCount;
  }

  public void setCoreTeamsCount(Long coreTeamsCount)
  {
    this.coreTeamsCount = coreTeamsCount;
  }

  public Long getCoreTeamPlayersCount()
  {
    return coreTeamPlayersCount;
  }

  public void setCoreTeamPlayersCount(Long coreTeamPlayersCount)
  {
    this.coreTeamPlayersCount = coreTeamPlayersCount;
  }

  public Long getFreeSubstututions()
  {
    return freeSubstututions;
  }

  public void setFreeSubstututions(Long freeSubstututions)
  {
    this.freeSubstututions = freeSubstututions;
  }

  public String getUpdateUserTeamCutOffDate()
  {
    return updateUserTeamCutOffDate;
  }

  public void setUpdateUserTeamCutOffDate(String updateUserTeamCutOffDate)
  {
    this.updateUserTeamCutOffDate = updateUserTeamCutOffDate;
  }

}
