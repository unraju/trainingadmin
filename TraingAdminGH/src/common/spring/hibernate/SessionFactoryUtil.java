package common.spring.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class SessionFactoryUtil
{

	///*@Autowired(required = true)
	private static SessionFactory sessionFactory;
	
 // private static org.hibernate.SessionFactory sessionFactory;

  /** * disable contructor to guaranty a single instance */
  private SessionFactoryUtil()
  {
  }

 /* static
  {
    
    sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
  }*/

  public static SessionFactory getInstance()
  {
    return sessionFactory;
  }

  public static Session getSession()
  {
    return sessionFactory.openSession();
  }

}
