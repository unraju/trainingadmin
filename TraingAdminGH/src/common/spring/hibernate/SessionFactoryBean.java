package common.spring.hibernate;

import org.hibernate.SessionFactory;

public class SessionFactoryBean
{

  private SessionFactory sessionFactory;

  public void setSessionFactory(SessionFactory sessionFactory)
  {
    this.sessionFactory = sessionFactory;
  }

  /** * disable contructor to guaranty a single instance */
  private SessionFactoryBean()
  {
  }

  public SessionFactory getSessionFactoty()
  {
    return sessionFactory;
  }

}
