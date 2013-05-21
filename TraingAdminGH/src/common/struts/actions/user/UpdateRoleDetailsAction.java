package common.struts.actions.user;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.actionforms.user.ActivityForm;
import common.struts.actionforms.user.UserRoleForm;
import common.struts.helpers.user.ManageUserDBHelper;
import common.util.CheckSessionValidate;
import common.util.Constants;

public class UpdateRoleDetailsAction extends org.apache.struts.action.Action
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		CheckSessionValidate.isSessionValide(request, response);
		HttpSession httpSession = request.getSession();
		String operation = request.getParameter("operation");
		// AddExpActionForm addExpActionForm = (AddExpActionForm)form;
		String forward = "error";
		UserRoleForm userRoleForm = (UserRoleForm) form;
		if (operation.equalsIgnoreCase("Save"))
		{
			List<ActivityForm> activityList = new ArrayList<ActivityForm>();
			for (ActivityForm activityForm : ManageUserDBHelper.getAllRoleActivities())
			{
				String value = (String) request.getParameter(activityForm.getId().toString());
				if (value != null && value.equals("Selected"))
				{
					activityList.add(activityForm);
				}
			}
			userRoleForm.setActivities(activityList);
			ManageUserDBHelper.updateRoleActivities(userRoleForm);
			forward = "sucess";
			httpSession.setAttribute(common.util.Constants.SEARCHED_ROLES, ManageUserDBHelper.getAllUserRoles());
		}
		else if (operation.equalsIgnoreCase("Cancel"))
		{
			forward = "cancel";
		}
		else if (operation.equalsIgnoreCase("Delete"))
		{

			forward = "delete";
			if (userRoleForm.isActive())
			{
				request.setAttribute(Constants.VALIDATION_MESSAGE, Constants.ACTIVE_ROLE);
				return mapping.findForward(forward);
			}
			try
			{
				ManageUserDBHelper.deleteRole(userRoleForm.getId());
				// searchedRoleList.remove(userRoleForm);
				// httpSession.setAttribute(Constants.SEARCHED_ROLES,
				// searchedRoleList);
			}
			catch (Exception e)
			{
				request.setAttribute(Constants.VALIDATION_MESSAGE, e.getLocalizedMessage());
			}
		}
		httpSession.setAttribute(Constants.USER_ROLES, ManageUserDBHelper.getAllUserRoles());
		return mapping.findForward(forward);

	}

}
