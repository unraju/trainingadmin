package common.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.hibernate.bf.user.User;

public class CheckSessionValidate
{

  public static void isSessionValide(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    HttpSession httpSession = request.getSession(false);
    if (httpSession == null || (httpSession != null && httpSession.getAttribute(Constants.USER) == null))
    {
      httpSession.invalidate();
      request.removeAttribute("operation_error");
      request.removeAttribute("serchedresults");
      request.removeAttribute("error");
    /*  RequestDispatcher dispatcher = request.getRequestDispatcher("sessionTimeoutWebXml.jsp");
      dispatcher.forward(request, response);*/
    }
  }
  
  public static boolean isInValidSession(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    HttpSession httpSession = request.getSession(false);
    boolean valide = false;
    if (httpSession == null || (httpSession != null && httpSession.getAttribute(Constants.USER) == null))
    {
      httpSession.invalidate();
      request.removeAttribute("operation_error");
      request.removeAttribute("serchedresults");
      request.removeAttribute("error");
     /* RequestDispatcher dispatcher = request.getRequestDispatcher("sessionTimeoutWebXml.jsp");
      dispatcher.forward(request, response);*/
      valide = true;
    }
    return valide;
  }

  
  public static ActionForward isSessionValide(HttpServletRequest request, ActionMapping mapping) 
  {
    HttpSession httpSession = request.getSession(false);
    User user = (User) httpSession.getAttribute(Constants.USER);
    if(user == null)
    {
     throw new RuntimeException(Constants.USER_NOT_SIGNED_IN);
    }
    return null;
  }
}
