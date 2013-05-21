package common.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import common.struts.helpers.ErrorLogHelper;
import common.util.HibernateUtil;
import common.util.UserUtil;

public class HibernateSessionFilter implements Filter {
	private static Log log = LogFactory.getLog(HibernateSessionFilter.class);

	// private ServletContext ctx = null;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		try
		{
			log.debug("Starting a database transaction");
			HibernateUtil.beginTransaction();
			chain.doFilter(request, response);
			log.debug("Committing the database transaction -  Before");
			HibernateUtil.commitTransaction();
			log.debug("Committing the database transaction -  End");
			
		}
		catch (ServletException ex)
		{
			// Rollback only
			ex.printStackTrace();
			try
			{
				log.debug("Trying to rollback database transaction after exception");
				HibernateUtil.rollbackTransaction();
			}
			catch (Throwable rbEx)
			{
				log.error("Could not rollback transaction after exception!", rbEx);
				rbEx.printStackTrace();
			}
			try
			{
				new ErrorLogHelper().logError(ex.getRootCause());
			}
			catch (Exception e)
			{
				throw new ServletException(e);
			}
			String forwardAction = "error.do";
			String urlWithSessionID = null;
			if (forwardAction != null)
			{
				urlWithSessionID = res.encodeRedirectURL(forwardAction);
			}
			res.sendRedirect(urlWithSessionID);
		}
		catch (IOException ex)
		{
			// Rollback only
			ex.printStackTrace();
			try
			{
				log.debug("Trying to rollback database transaction after exception");
				HibernateUtil.rollbackTransaction();
			}
			catch (Throwable rbEx)
			{
				log.error("Could not rollback transaction after exception!", rbEx);
				// throw new ServletException(rbEx);
			}
			try
			{
				if (UserUtil.isLogNeeded())
				{
					new ErrorLogHelper().logError(ex);
				}
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			String forwardAction = "error.do";
			String urlWithSessionID = null;
			if (forwardAction != null)
			{
				urlWithSessionID = res.encodeRedirectURL(forwardAction);
			}
			res.sendRedirect(urlWithSessionID);
			throw new ServletException(ex);
		}
		finally
		{
			HibernateUtil.closeSession();
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		log.debug("Initializing filter...");
		// ctx =filterConfig.getServletContext();
	}

	public void destroy() {
	}

}