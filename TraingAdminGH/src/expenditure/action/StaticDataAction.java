package expenditure.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.actionforms.user.UserForm;
import common.util.CheckSessionValidate;
import common.util.Constants;

import expenditure.actionform.StaticDataForm;
import expenditure.helpers.StaticDBHelper;

public class StaticDataAction extends Action
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{

		CheckSessionValidate.isSessionValide(request, response);
		StaticDataForm sdForm = (StaticDataForm) form;
		String forward = "success";

		try
		{

			StaticDBHelper helper = new StaticDBHelper();
			HttpSession httpSession = request.getSession(false);
			UserForm userForm = (UserForm) httpSession.getAttribute(Constants.USER);
			// sdForm.setFinanceItemType(loginUser.isFinanceUser());
			helper.addItemDetails(sdForm);
			// System.out.println("### SDForm ###" + sdForm);
			sdForm = null;
			request.setAttribute("addStaticForm", sdForm);
			request.setAttribute("success", "Detals added Sucessfully");

		}
		catch (Exception e)
		{
			e.printStackTrace();
			request.setAttribute("error", e.getLocalizedMessage());
			forward = "error";
		}
		return mapping.findForward(forward);
	}
}
