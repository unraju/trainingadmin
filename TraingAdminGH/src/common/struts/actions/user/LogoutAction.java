package common.struts.actions.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.util.Constants;

public class LogoutAction extends Action
{

  @Override
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
  {
    
    HttpSession httpSession = request.getSession();
    httpSession.removeAttribute(Constants.USER);
    httpSession.removeAttribute(Constants.USER_FORM);
    //httpSession.removeAttribute(Constants.USER_ROLES);
    if (httpSession != null)
    {
      //httpSession.invalidate();  
    }
    //ServletContext ctx = getServlet().getServletContext();
    //httpSession.setAttribute(Constants.USER_ROLES, ctx.getAttribute(Constants.USER_ROLES));
    request.setAttribute(Constants.ERROR_MESSAGE ,  "You are successfully logged out.");
   
    
    String sessionTimeOut =(String)request.getParameter("sessionTimeout");
    if(sessionTimeOut != null && sessionTimeOut.equalsIgnoreCase("true"))
    {
      request.setAttribute(Constants.ERROR_MESSAGE,"Your Session was expired. Please Re-Login");
     // System.out.println("Session Invalidated.... Moving to Session Logout Page");
      //return mapping.findForward(Constants.INVALID_SESSION);
      //return mapping.findForward("success");
    }
    return mapping.findForward("success");
  }

}
