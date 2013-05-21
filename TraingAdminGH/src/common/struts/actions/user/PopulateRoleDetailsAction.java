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

public class PopulateRoleDetailsAction extends org.apache.struts.action.Action
{

  @Override
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
  {
    CheckSessionValidate.isSessionValide(request, response);
    String operation = request.getParameter("operation");
    String id = request.getParameter("id");
    HttpSession httpSession = request.getSession();
    long selectedUserId = 0;
    if (id != null)
    {
      selectedUserId = Integer.parseInt(request.getParameter("id"));
    }
    String forward = "error";
    UserRoleForm userRoleForm = null;
   
    List<UserRoleForm> searchedRoleList = (ArrayList<UserRoleForm>) httpSession.getAttribute(Constants.SEARCHED_ROLES);

    if (operation.equalsIgnoreCase("Update") || operation.equalsIgnoreCase("Delete"))
    {
      if (selectedUserId == 0)
      {
        request.setAttribute(Constants.VALIDATION_MESSAGE,  "Select Role to be Deleted.");
        forward = "validation";
        return mapping.findForward(forward);
      }
      for (UserRoleForm tempRole : searchedRoleList)
      {
        if (selectedUserId == tempRole.getId().longValue())
        {
          userRoleForm = tempRole;
          break;
        }
      }
    }

    if (operation.equalsIgnoreCase("Update"))
    {
      forward = "update";
      List<ActivityForm> allActivities = ManageUserDBHelper.getAllRoleActivities();
      httpSession.setAttribute(Constants.ROLE_ACTIVITIES, allActivities);
      userRoleForm.setAllActivities(allActivities);
      for (ActivityForm activityForm : userRoleForm.getActivities())
      {
        for (ActivityForm activityForm1 : userRoleForm.getAllActivities())
        {
          if (activityForm.getId().longValue() == activityForm1.getId().longValue())
          {
            activityForm1.setChecked(true);
          }
        }
      }
      httpSession.setAttribute("userRoleForm", userRoleForm);
      request.setAttribute("userRoleForm", userRoleForm);
      // HibernateDBHelper.updateExpDetails(form,
      // request.getSession().getAttribute(Constants.USER));
    }
      
    httpSession.setAttribute(Constants.OPERATION, operation);
    httpSession.setAttribute(Constants.USER_ROLES, ManageUserDBHelper.getAllUserRoles());
    return mapping.findForward(forward);
   

  }

}
