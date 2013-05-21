package common.struts.actions.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.actionforms.user.ChangePwdForm;
import common.struts.actionforms.user.UserForm;
import common.struts.helpers.user.LoginDBHelper;
import common.util.CheckSessionValidate;
import common.util.Constants;

public class ChangePwdAction extends Action
{

  @Override
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
  {

    CheckSessionValidate.isSessionValide(request, response);
    ChangePwdForm cPwdForm = (ChangePwdForm) form;
    request.removeAttribute(Constants.ERROR_MESSAGE);
    String operation = (String) request.getParameter("operation");

    if (operation != null && operation.equalsIgnoreCase("Cancel"))
    {
      return mapping.findForward("cancel");
    }
    String forward = "errorMessage";
    try
    {
      LoginDBHelper helper = new LoginDBHelper();
      helper.changepwdmethod(cPwdForm, (UserForm) request.getSession(false).getAttribute(Constants.USER));
      forward = "success";
      request.setAttribute(Constants.ERROR_MESSAGE, "Your Password Changed Successfully.");
    }
    catch (Exception e)
    {
      //e.printStackTrace();
      request.setAttribute(Constants.ERROR_MESSAGE, e.getLocalizedMessage());
    }
    cPwdForm = new ChangePwdForm();
    request.setAttribute(Constants.CHANGE_PWD_FORM, cPwdForm);
    return mapping.findForward(forward);
  }

}
