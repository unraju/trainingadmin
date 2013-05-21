package common.util;

import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Interceptor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import common.exceptions.InfrastructureException;

/**
 * Basic Hibernate helper class, handles SessionFactory, Session and
 * Transaction.
 * <p>
 * Uses a static initializer for the initial SessionFactory creation and holds
 * Session and Transactions in thread local variables. All exceptions are
 * wrapped in an unchecked InfrastructureException.
 * 
 * @author christian@hibernate.org
 */
public class CopyOfHibernateUtil {

	private static Log log = LogFactory.getLog(CopyOfHibernateUtil.class);

	private static Configuration configuration;
	//private static SessionFactory sessionFactory;
	private static final ThreadLocal threadSession = new ThreadLocal();
	private static final ThreadLocal threadTransaction = new ThreadLocal();
	private static final ThreadLocal threadInterceptor = new ThreadLocal();
	
	@Autowired(required = true)
	private static SessionFactory sessionFactory;

	static ApplicationContext ctx = null;
	// Create the initial SessionFactory from the default configuration files
	static
	{
		try
		{

			// Getting the connection direct from Configuration
			/*
			 * configuration = new Configuration(); sessionFactory =
			 * configuration.configure( // "hibernate.cfg_l.xml"
			 * "hibernate.cfg.xml" ).buildSessionFactory();
			 */

			// getting the connection from Spring FrameWork.
			//ctx = new ClassPathXmlApplicationContext("common/spring/hibernate/session_factory_config.xml");
			//sessionFactory = ((SessionFactoryBean) ctx.getBean("sessionFactory")).getSessionFactoty();

			// We could also let Hibernate bind it to JNDI:
			// configuration.configure().buildSessionFactory()
		}
		catch (Throwable ex)
		{
			// We have to catch Throwable, otherwise we will miss
			// NoClassDefFoundError and other subclasses of Error
			log.error("Building SessionFactory failed.", ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	/**
	 * Returns the SessionFactory used for this static class.
	 * 
	 * @return SessionFactory
	 */
	public static SessionFactory getSessionFactory() {
		/*
		 * Instead of a static variable, use JNDI: SessionFactory sessions =
		 * null; try { Context ctx = new InitialContext(); String jndiName =
		 * "java:hibernate/HibernateFactory"; sessions =
		 * (SessionFactory)ctx.lookup(jndiName); } catch (NamingException ex) {
		 * throw new InfrastructureException(ex); } return sessions;
		 */
		return sessionFactory;
	}

	/**
	 * Returns the original Hibernate configuration.
	 * 
	 * @return Configuration
	 */
	public static Configuration getConfiguration() {
		return configuration;
	}

	/**
	 * Rebuild the SessionFactory with the static Configuration.
	 * 
	 */
	public static void rebuildSessionFactory() throws InfrastructureException {
		synchronized (sessionFactory)
		{
			try
			{
				sessionFactory = getConfiguration().buildSessionFactory();
			}
			catch (Exception ex)
			{
				throw new InfrastructureException(ex);
			}
		}
	}

	/**
	 * Rebuild the SessionFactory with the given Hibernate Configuration.
	 * 
	 * @param cfg
	 */
	public static void rebuildSessionFactory(Configuration cfg) throws InfrastructureException {
		synchronized (sessionFactory)
		{
			try
			{
				sessionFactory = cfg.buildSessionFactory();
				configuration = cfg;
			}
			catch (Exception ex)
			{
				throw new InfrastructureException(ex);
			}
		}
	}

	/**
	 * Retrieves the current Session local to the thread.
	 * <p/>
	 * If no Session is open, opens a new Session for the running thread.
	 * 
	 * @return Session
	 */
	/*
	 * public static Session getSession() { return
	 * getSessionFactory().getCurrentSession(); }
	 */

	public static Session getSession() {
		log.debug("Entering in to getSession().");
		Session session = (Session) threadSession.get();
		try
		{
			if (session == null)
			{
				log.debug(" Session is null for Current Thread.");
				/*
				 * if (getInterceptor() != null) {
				 * log.debug("Using interceptor: " +
				 * getInterceptor().getClass()); s =
				 * getSessionFactory().openSession(getInterceptor()); log.debug(
				 * " New  Session Opned for for Current Thread & Interpreter.");
				 * } else {
				 */
				log.debug(" New Session Created for Current Thread.");
				session = getSessionFactory().openSession();
				// }
				threadSession.set(session);
			}
			else
			{
				log.debug(" Session Found for Current Thread.");
			}

		}
		catch (HibernateException ex)
		{
			// throw new InfrastructureException(ex);
		}
		log.debug("Exiting in to getSession().");
		return session;
	}

	/**
	 * Closes the Session local to the thread.
	 */
	public static void closeSession() throws ServletException {
		try
		{
			Session session = (Session) threadSession.get();
			threadSession.set(null);
			if (session != null && session.isOpen())
			{
				log.debug("Closing Session of this thread.");
				session.close();
			}
		}
		catch (HibernateException ex)
		{
			throw new ServletException(ex);
		}
	}

	/**
	 * Start a new database transaction.
	 */
	public static void beginTransaction() throws ServletException {
		Transaction tx = (Transaction) threadTransaction.get();
		try
		{
			if (tx == null)
			{
				log.debug("Starting new database transaction in this thread.");
				tx = getSession().beginTransaction();
				threadTransaction.set(tx);
			}
		}
		catch (HibernateException ex)
		{
			throw new ServletException(ex);
		}
	}

	/**
	 * Commit the database transaction.
	 */
	public static void commitTransaction() throws ServletException {
		Transaction tx = (Transaction) threadTransaction.get();
		try
		{
			if (tx != null && !tx.wasCommitted() && !tx.wasRolledBack())
			{
				log.debug("Committing database transaction of this thread. Before");
				tx.commit();
				log.debug("Committing database transaction of this thread. After");
			}
			threadTransaction.set(null);
		}
		catch (HibernateException ex)
		{
			rollbackTransaction();
			throw new ServletException(ex);
		}
	}

	/**
	 * Rollback the database transaction.
	 */
	public static void rollbackTransaction() throws ServletException {
		Transaction tx = (Transaction) threadTransaction.get();
		try
		{
			threadTransaction.set(null);
			if (tx != null && !tx.wasCommitted() && !tx.wasRolledBack())
			{
				log.debug("Tyring to rollback database transaction of this thread.");
				tx.rollback();
			}
		}
		catch (HibernateException ex)
		{
			throw new ServletException(ex);
		}
		finally
		{
			closeSession();
		}
	}

	/**
	 * Reconnects a Hibernate Session to the current Thread.
	 * 
	 * @param session
	 *            The Hibernate Session to be reconnected.
	 */
	public static void reconnect(Session session) throws InfrastructureException {
		try
		{
			session.reconnect();
			threadSession.set(session);
		}
		catch (HibernateException ex)
		{
			throw new InfrastructureException(ex);
		}
	}

	/**
	 * Disconnect and return Session from current Thread.
	 * 
	 * @return Session the disconnected Session
	 */
	public static Session disconnectSession() throws InfrastructureException {

		Session session = getSession();
		try
		{
			threadSession.set(null);
			if (session.isConnected() && session.isOpen())
				session.disconnect();
		}
		catch (HibernateException ex)
		{
			throw new InfrastructureException(ex);
		}
		return session;
	}

	/**
	 * Register a Hibernate interceptor with the current thread.
	 * <p>
	 * Every Session opened is opened with this interceptor after registration.
	 * Has no effect if the current Session of the thread is already open,
	 * effective on next close()/getSession().
	 */
	public static void registerInterceptor(Interceptor interceptor) {
		threadInterceptor.set(interceptor);
	}

	private static Interceptor getInterceptor() {
		Interceptor interceptor = (Interceptor) threadInterceptor.get();
		return interceptor;
	}

	public static void setSessionFactory(SessionFactory sessionFactory) {
		CopyOfHibernateUtil.sessionFactory = sessionFactory;
	}

}
