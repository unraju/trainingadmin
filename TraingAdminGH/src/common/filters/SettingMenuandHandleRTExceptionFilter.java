package common.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SettingMenuandHandleRTExceptionFilter implements Filter
{

  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException
  {
    //System.out.println("### Pre Handle Request ###");
    HttpServletRequest request = (HttpServletRequest) req;
    HttpServletResponse response = (HttpServletResponse) res;
    //Get the IP address of client machine.
    String ipAddress = request.getRemoteAddr();

   // System.out.println("Time : " + new Date().toString() + " Current Thread Id : " + Thread.currentThread().getId() + " /n Current Thread Name : "
      //  + Thread.currentThread().getName() + " Current Thread Active Count : " + Thread.currentThread().activeCount());

    /*if (false) {
    	response.sendRedirect("error.jsp");
    	} else {
    	chain.doFilter(req,res);
    	}*/

    try
    {
      chain.doFilter(req, res);
    }
    catch (Exception e)
    {
      //System.out.println("### RuntimeException Occured");
      e.printStackTrace();
      response.sendRedirect("error.jsp");
    }
    //System.out.println("### Post Handle Request ###");
  }

  public void init(FilterConfig config) throws ServletException
  {

    //Get init parameter
    String testParam = config.getInitParameter("test-param-SetMenuDetailsFilter");
    //Print the init parameter
    //System.out.println("Test Param: " + testParam);
  }

  public void destroy()
  {
    //add code to release any resource
  }
}