package cricket.hibernate.bf.common;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import common.util.HibernateUtil;

public class TeamGroupHome
{

  public static List<TeamGroup> getTeamGroupStaticData()
  {
  Session session = HibernateUtil.getSession();
  session.beginTransaction();
  Criteria criteria = session.createCriteria(TeamGroup.class);
  List<TeamGroup> list = criteria.list();

  return list;
  }

  public static TeamGroup findTeamGroupById(Long id)
  {
  TeamGroup teamGroup = null;
  Session session = HibernateUtil.getSession();
  Criteria criteria = session.createCriteria(TeamGroup.class);
  criteria.add(Restrictions.eq("id", id));
  List<TeamGroup> list = criteria.list();
  if (list.size() > 0)
  {
    teamGroup = list.get(0);
  }
  return teamGroup;
  }

}
