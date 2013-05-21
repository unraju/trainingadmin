package common.struts.actions.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.hibernate.bf.user.User;
import common.struts.actionforms.user.ForgotPwdForm;
import common.struts.helpers.user.LoginDBHelper;
import common.util.Constants;

public class CopyOfForGotPwdAction extends Action
{

  @Override
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
  {

    ForgotPwdForm userForm = (ForgotPwdForm) form;
    request.removeAttribute(Constants.ERROR_MESSAGE);

    String operation = (String) request.getParameter("operation");

    if (operation != null && operation.equalsIgnoreCase("Cancel"))
    {
      return mapping.findForward("cancel");
    }

    String forward = "error";
    try
    {
      LoginDBHelper helper = new LoginDBHelper();
      List<User> list = null;//helper.handleForgotPWD(userForm);
      if (list.size() > 0)
      {
        request.setAttribute("forgotPwdForm", new ForgotPwdForm());
        request.setAttribute(Constants.ERROR_MESSAGE, "Password changed successfully. Try to login...");
        forward = "success";
      }
      else
      {
        forward = "errorMessage";
        request.setAttribute(Constants.ERROR_MESSAGE, "Entered details are not matched. Please try again..");
      }

    }
    catch (Exception e)
    {
      e.printStackTrace();
      request.setAttribute(Constants.ERROR_MESSAGE, e.getLocalizedMessage());
      forward = "errorMessage";
    }

    return mapping.findForward(forward);
  }

}
