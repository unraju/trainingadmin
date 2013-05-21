package common.struts.actions.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.actionforms.user.UserForm;
import common.struts.helpers.user.LoginDBHelper;
import common.util.Constants;

public class NewLoginAction extends Action
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		request.removeAttribute("error");
		UserForm userForm = (UserForm) form;
		userForm.setLoginName(userForm.getEmailId());
		String forward = "success";
		String operation = (String) request.getParameter("operation");

		if (operation != null && operation.equalsIgnoreCase("Cancel"))
		{
			return mapping.findForward("cancel");
		}

		try
		{
			LoginDBHelper.addLoginDetails(userForm);
			request.setAttribute(Constants.ERROR_MESSAGE, "Registered Sucessfully, Please Login.");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			request.setAttribute("error", e.getLocalizedMessage());
			forward = "error";
			request.setAttribute("success", "");
		}
		return mapping.findForward(forward);
	}

}
