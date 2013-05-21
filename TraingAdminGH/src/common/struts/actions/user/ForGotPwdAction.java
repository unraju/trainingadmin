package common.struts.actions.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.actionforms.user.ForgotPwdForm;
import common.struts.helpers.user.LoginDBHelper;
import common.util.Constants;

public class ForGotPwdAction extends Action
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{

		ForgotPwdForm userForm = (ForgotPwdForm) form;
		request.removeAttribute(Constants.ERROR_MESSAGE);
		String message = "";
		String forward = "success";

		String operation = (String) request.getParameter("operation");

		if (operation != null && operation.equalsIgnoreCase("Cancel"))
		{
			return mapping.findForward("cancel");
		}

		if (userForm.getEmailId() == null || userForm.getEmailId().trim().equals(""))
		{
			message = "Please enter the valid mailId";
			request.setAttribute(Constants.ERROR_MESSAGE, message);
			return mapping.findForward(forward);
		}

		try
		{
			LoginDBHelper helper = new LoginDBHelper();
			message = helper.handleForgotPWD(userForm);

		}
		catch (Exception e)
		{
			e.printStackTrace();
			request.setAttribute(Constants.ERROR_MESSAGE, e.getLocalizedMessage());
			forward = "errorMessage";
		}
		request.setAttribute(Constants.ERROR_MESSAGE, message);

		return mapping.findForward(forward);
	}

}
