package cricket.hibernate.bf.common;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import common.util.HibernateUtil;

public class CountryHome
{

  public static List<Country> getCountryStaticData()
  {
  Session session = HibernateUtil.getSession();
  session.beginTransaction();
  Criteria criteria = session.createCriteria(Country.class);
  List<Country> list = criteria.list();

  return list;
  }

  public static Country findCountryById(Long id)
  {
  Country country = null;
  Session session = HibernateUtil.getSession();
  Criteria criteria = session.createCriteria(Country.class);
  criteria.add(Restrictions.eq("id", id));
  List<Country> list = criteria.list();
  if (list.size() > 0)
  {
    country = list.get(0);
  }
  return country;
  }

}
