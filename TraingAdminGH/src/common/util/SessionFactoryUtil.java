package common.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryUtil
{

  private static org.hibernate.SessionFactory sessionFactory;

  /** * disable contructor to guaranty a single instance */
  private SessionFactoryUtil()
  {
  }

  static
  {

    sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
  }

  public static SessionFactory getInstance()
  {
    return sessionFactory;
  }

  public static Session getSession()
  {
    return sessionFactory.openSession();
  }

}
