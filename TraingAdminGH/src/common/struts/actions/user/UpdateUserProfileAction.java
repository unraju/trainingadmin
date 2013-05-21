package common.struts.actions.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.actionforms.user.UserForm;
import common.struts.helpers.user.LoginDBHelper;
import common.util.CheckSessionValidate;
import common.util.Constants;

public class UpdateUserProfileAction extends Action
{

  @Override
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
  {
    CheckSessionValidate.isSessionValide(request, response);
    HttpSession httpSession = request.getSession();
    String operation = request.getParameter("operation");
    // AddExpActionForm addExpActionForm = (AddExpActionForm)form;
    String forward = "success";
    UserForm userForm = (UserForm) form;
    if(userForm == null || (userForm != null && userForm.getId() == null))
    {
      userForm = (UserForm)httpSession.getAttribute(Constants.USER);
    }
    try
    {
      if (operation != null && operation.equalsIgnoreCase("Confirm"))
      {
        UserForm form2 = LoginDBHelper.updateUserProfile(userForm);
        httpSession.setAttribute(Constants.USER, form2);
        forward = "success";
        request.setAttribute(Constants.ERROR_MESSAGE, "Your details modified successfully.");
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      request.setAttribute(Constants.ERROR_MESSAGE, e.getLocalizedMessage());
    }
    
    request.setAttribute(Constants.USER_FORM, LoginDBHelper.findUserById(userForm.getId()).getUserForm());

    return mapping.findForward(forward);

  }

}
