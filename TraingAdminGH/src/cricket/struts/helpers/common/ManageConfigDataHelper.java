package cricket.struts.helpers.common;

import java.text.ParseException;
import java.util.Collection;
import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import common.util.DateUtil;
import common.util.HibernateUtil;

import cricket.hibernate.bf.config.ConfigData;
import cricket.struts.actionforms.common.ConfigDataForm;

public class ManageConfigDataHelper
{

  public ConfigDataForm getConfigData()
  {

    Session session = HibernateUtil.getSession();
    Criteria criteria = session.createCriteria(ConfigData.class);
    Collection<ConfigData> list = criteria.list();
    for (ConfigData configData : list)
    {
      if (configData != null)
      {
        return configData.getActionForm();
      }
    }
    return null;
  }

  public void saveConfigData(ConfigDataForm configDataForm) throws Exception
  {
    Session session = HibernateUtil.getSession();
    ConfigData configData = null;
    if (configDataForm.getId() != null && configDataForm.getId() != 0)
    {
      configData = findConfigDataById(configDataForm.getId());
    }
    else
    {
      configData = new ConfigData();
    }
    configData.setCoreTeamPlayersCount(configDataForm.getCoreTeamPlayersCount());
    configData.setCoreTeamsCount(configDataForm.getCoreTeamsCount());
    configData.setFreeSubstututions(configDataForm.getFreeSubstututions());
    if (configDataForm.getUpdateUserTeamCutOffDate() != null)
    {
      Date date = null;
      try
      {
        date = DateUtil.getDateFromStringFormattedDDMMYYYY(configDataForm.getUpdateUserTeamCutOffDate());
      }
      catch (ParseException e)
      {

        e.printStackTrace();
        throw e;
      }
      configData.setUpdateUserTeamCutOffDate(date);
    }
    session.save(configData);
  }

  private ConfigData findConfigDataById(Long id)
  {
    Session session = HibernateUtil.getSession();
    Criteria criteria = session.createCriteria(ConfigData.class);
    criteria.add(Restrictions.eq("id", id));
    Collection<ConfigData> list = criteria.list();
    for (ConfigData configData : list)
    {
      if (configData != null)
      {
        return configData;
      }
    }
    return null;
  }

}
