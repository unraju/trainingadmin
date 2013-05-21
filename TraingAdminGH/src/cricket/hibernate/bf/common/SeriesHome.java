package cricket.hibernate.bf.common;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import common.util.HibernateUtil;

public class SeriesHome
{

  public static List<Series> getSeriesStaticData()
  {
  Session session = HibernateUtil.getSession();
  session.beginTransaction();
  Criteria criteria = session.createCriteria(Series.class);
  List<Series> list = criteria.list();

  return list;
  }

  public static Series findSeriesById(Long id)
  {
  Series series = null;
  Session session = HibernateUtil.getSession();
  Criteria criteria = session.createCriteria(Series.class);
  criteria.add(Restrictions.eq("id", id));
  List<Series> list = criteria.list();
  if (list.size() > 0)
  {
    series = list.get(0);
  }
  return series;
  }

}
