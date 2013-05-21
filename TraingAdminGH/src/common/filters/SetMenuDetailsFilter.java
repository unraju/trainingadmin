package common.filters;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.ehcache.exception.CacheElementNotFound;
import common.exceptions.InfrastructureException;
import common.struts.actionforms.user.UserForm;
import common.struts.actionforms.user.UserRoleForm;
import common.util.Constants;
import common.util.RetriveContextData;
import common.util.UserUtil;

public class SetMenuDetailsFilter implements Filter
{
	private ServletContext ctx = null;

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
			ServletException
	{
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		try
		{
			forwardMenuDetails(request, response);

			chain.doFilter(req, res);
		}
		catch (NullPointerException e)
		{
			e.printStackTrace();
			throw new ServletException(e);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

	public void init(FilterConfig config) throws ServletException
	{
		ctx = config.getServletContext();
	}

	public void destroy()
	{
		// add code to release any resource
	}

	private void forwardMenuDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			InfrastructureException
	{
		HttpSession session = request.getSession();
		String menu = (String) request.getParameter("menu");
		String submenu = (String) request.getParameter("submenu");
		String path = (String) request.getRequestURI();

		if (menu == null && session.getAttribute("menu") != null)
		{
			menu = (String) session.getAttribute("menu");
		}

		if (path != null && path.contains("prelogin"))
		{
			menu = "108";
		}

		if (menu == null)
		{
			menu = "108";
		}
		if (menu != null)
		{
			session.setAttribute("menu", menu);
		}
		if (submenu != null)
		{
			session.setAttribute("submenu", submenu);
		}

		List<UserRoleForm> userRoles;
		try
		{
			userRoles = new RetriveContextData().getUserRolesData(ctx);
			// userRoles = null;
			/*if (userRoles == null || userRoles.size() == 0)
			{
				new LoadStaticDataHelper().loadstaticData(ctx);
				userRoles = new RetriveContextData().getUserRolesData(ctx);
				HibernateUtil.disconnectSession();
			}*/
		}
		catch (CacheElementNotFound e)
		{
			throw new ServletException(e);
		}
		catch (Exception e)
		{
			throw new ServletException(e);
		}
		for (UserRoleForm roleForm : userRoles)
		{
			if (roleForm.getId() != null && roleForm.getId().toString().equals(menu) && roleForm.getSeriesId() != null)
			{
				UserUtil.setSelectedSeries(Long.parseLong(roleForm.getSeriesId()));
				if (submenu == null)
				{
					submenu = roleForm.getActivities().get(0).getId().toString();
					session.setAttribute("submenu", submenu);
				}
				break;
			}
			
		}
		/*
		 * else { menu = "90"; List<UserRoleForm> userRoles =
		 * (List<UserRoleForm>) ctx.getAttribute(Constants.USER_ROLES); for
		 * (UserRoleForm roleForm : userRoles) { if
		 * (roleForm.isUserAssociated()) {
		 * UserUtil.setSelectedSeries(Long.parseLong(roleForm.getSeriesId()));
		 * return; } } }
		 */
		/*
		 * if(menu != null && (menu.equals("90") || menu.equals("101") ||
		 * menu.equals("102"))) { UserUtil.setSelectedSeries(1L); } else if(menu
		 * != null && (menu.equals("108") || menu.equals("105") ||
		 * menu.equals("106"))) { UserUtil.setSelectedSeries(2L); }
		 */
		UserForm userForm = (UserForm) session.getAttribute(Constants.USER);
		if (userForm != null) UserUtil.setCurrentUser(userForm);
		// UserUtil.setSelectedSeries(1L);
		/*
		 * User user = (User) session.getAttribute(Constants.USER); if (user ==
		 * null) { // throw new RuntimeException(Constants.USER_NOT_SIGNED_IN);
		 * }
		 */
	}
}