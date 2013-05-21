package common.struts.actions.user;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.hibernate.bf.user.UserRole;
import common.struts.actionforms.user.ActivityForm;
import common.struts.actionforms.user.SearchRoleForm;
import common.struts.actionforms.user.UserRoleForm;
import common.struts.helpers.user.ManageUserDBHelper;
import common.util.CheckSessionValidate;
import common.util.Constants;

public class SearchRolesAction extends Action
{

  @Override
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
  {

    CheckSessionValidate.isSessionValide(request, response);
    HttpSession session = request.getSession();
    session.removeAttribute(Constants.SEARCHED_ROLES);
    SearchRoleForm searchRoleForm = (SearchRoleForm) form;
    String operation = request.getParameter("operation");
    String forward = "error";
    try
    {
      if (operation != null && operation.equals(Constants.SEARCH_BTN_VALUE))
      {
        List<UserRole> roles = ManageUserDBHelper.searchRoles(searchRoleForm);
        List<UserRoleForm> roleList = new ArrayList<UserRoleForm>();
        for (UserRole userRole : roles)
        {
          roleList.add(userRole.getUserRoleForm());
        }
        session.setAttribute(Constants.SEARCHED_ROLES, roleList);
        forward = "success";
      }
      else if (operation != null && operation.equals(Constants.ADD_BTN_VALUE))
      {
        UserRoleForm userRoleForm = new UserRoleForm();
        List<ActivityForm> allActivities = ManageUserDBHelper.getAllRoleActivities();
        session.setAttribute(Constants.ROLE_ACTIVITIES, allActivities);
        userRoleForm.setAllActivities(allActivities);
        session.setAttribute("userRoleForm", userRoleForm);
        request.setAttribute("serRoleForm", userRoleForm);
        forward = "add";
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      request.setAttribute("error", e.getLocalizedMessage());
    }
    return mapping.findForward(forward);
  }

}
