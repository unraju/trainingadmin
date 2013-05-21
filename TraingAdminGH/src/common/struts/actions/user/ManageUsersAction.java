package common.struts.actions.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.helpers.user.ManageUserDBHelper;
import common.util.CheckSessionValidate;
import common.util.Constants;

public class ManageUsersAction extends Action
{

  @Override
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
  {
    HttpSession session = request.getSession();
    session.removeAttribute(Constants.SEARCHED_ROLES);
    session.removeAttribute(Constants.SEARCHED_USERS);
    CheckSessionValidate.isSessionValide(request, response);
    String forward = "error";
    try
    {
      if (session.getAttribute(Constants.USER_ROLES) == null)
      {
        session.setAttribute(Constants.USER_ROLES, ManageUserDBHelper.getAllUserRoles());
      }
      forward = "success";
    }
    catch (Exception e)
    {
      e.printStackTrace();
      request.setAttribute("error", e.getLocalizedMessage());
    }
    return mapping.findForward(forward);
  }

}
