package cricket.hibernate.bf.config;

import common.util.DateUtil;

import cricket.struts.actionforms.common.ConfigDataForm;

public class ConfigData
{

  private Long id;

  private Long coreTeamsCount;

  private Long coreTeamPlayersCount;

  private Long freeSubstututions;

  private java.util.Date updateUserTeamCutOffDate;

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

  public java.util.Date getUpdateUserTeamCutOffDate()
  {
    return updateUserTeamCutOffDate;
  }

  public void setUpdateUserTeamCutOffDate(java.util.Date updateUserTeamCutOffDate)
  {
    this.updateUserTeamCutOffDate = updateUserTeamCutOffDate;
  }

  public ConfigDataForm getActionForm()
  {
    ConfigDataForm configDataForm = new ConfigDataForm();
    configDataForm.setId(id);
    configDataForm.setCoreTeamPlayersCount(coreTeamPlayersCount);
    configDataForm.setCoreTeamsCount(coreTeamsCount);
    configDataForm.setFreeSubstututions(freeSubstututions);
    if (updateUserTeamCutOffDate != null)
    {
      configDataForm.setUpdateUserTeamCutOffDate(DateUtil.getStringFromDate(this.updateUserTeamCutOffDate, "dd/MM/yyyy"));
    }
    return configDataForm;
  }
}
