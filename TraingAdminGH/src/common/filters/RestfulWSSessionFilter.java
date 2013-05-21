package common.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import common.util.HibernateUtil;

public class RestfulWSSessionFilter implements Filter
{

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException
	{

		try
		{

			HibernateUtil.beginTransaction();
			chain.doFilter(request, response);

			HibernateUtil.commitTransaction();

		}
		catch (Exception ex)
		{
			// Rollback only
			ex.printStackTrace();
			try
			{
				HibernateUtil.rollbackTransaction();
			}
			catch (Throwable rbEx)
			{
				rbEx.printStackTrace();
			}

		}

		finally
		{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public void destroy()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException
	{
		// TODO Auto-generated method stub

	}

}