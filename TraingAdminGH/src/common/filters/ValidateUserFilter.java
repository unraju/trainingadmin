package common.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.util.Constants;

public class ValidateUserFilter implements Filter
{

  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException
  {
    HttpServletRequest request = (HttpServletRequest) req;
    HttpServletResponse response = (HttpServletResponse) res;

    //System.out.println("### Pre Handle Request ValidateUserFilter ###");
    if (isSessionValide(request, response))
    {
      response.sendRedirect("sessionTimeoutWebXml.jsp");
      return;
    }

    try
    {
      chain.doFilter(req, res);
    }
    catch (Exception e)
    {
     // System.out.println("### RuntimeException Occured");
      e.printStackTrace();
      // response.sendRedirect("error.jsp");
      RequestDispatcher dispatcher = request.getRequestDispatcher("sessionTimeoutWebXml.jsp");
      dispatcher.forward(request, response);
    }
    //System.out.println("### Post Handle Request ValidateUserFilter ###");
  }

  public void init(FilterConfig config) throws ServletException
  {

    // Get init parameter
    String testParam = config.getInitParameter("test-param-ValidateUserFilter");
    // Print the init parameter
    //System.out.println("Test Param: " + testParam);
  }

  public void destroy()
  {
    // add code to release any resource
  }

  private boolean isSessionValide(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    HttpSession httpSession = request.getSession(false);
    if (httpSession == null || (httpSession != null && httpSession.getAttribute(Constants.USER) == null))
    {
      return true;
      /*
       * httpSession.invalidate(); request.removeAttribute("operation_error");
       * request.removeAttribute("serchedresults");
       * request.removeAttribute("error"); RequestDispatcher dispatcher =
       * request.getRequestDispatcher("sessionTimeoutWebXml.jsp");
       * dispatcher.forward(request, response);
       */
    }
    return false;
  }
}