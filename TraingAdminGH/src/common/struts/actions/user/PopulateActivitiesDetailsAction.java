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
import common.struts.helpers.user.ManageUserDBHelper;
import common.util.CheckSessionValidate;
import common.util.Constants;

public class PopulateActivitiesDetailsAction extends org.apache.struts.action.Action
{

  @Override
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
  {

    CheckSessionValidate.isSessionValide(request, response);
    String operation = request.getParameter("operation");
    String id = request.getParameter("id");
    long selectedUserId = 0;
    if (id != null)
    {
      selectedUserId = Integer.parseInt(request.getParameter("id"));
    }
    String forward = "error";
    if (operation.equalsIgnoreCase("delete"))
    {
      if (selectedUserId == 0)
      {
        request.setAttribute(Constants.VALIDATION_MESSAGE,  "Select Activity to be Deleted.");
        forward = "validation";
        return mapping.findForward(forward);
      }
    }

    HttpSession httpSession = request.getSession();
    ActivityForm activityForm = null;
    List<ActivityForm> searchedActivityList = (ArrayList<ActivityForm>) httpSession.getAttribute(Constants.SEARCHED_ACTIVITIES);
    for (ActivityForm activityForm2 : searchedActivityList)
    {
      if (selectedUserId == activityForm2.getId().longValue())
      {
        activityForm = activityForm2;
        break;
      }
    }
    if (operation.equalsIgnoreCase("Update"))
    {
      forward = "update";
    }
    else if (operation.equalsIgnoreCase("Delete"))
    {
      forward = "delete";
      if (activityForm.isActive())
      {
        request.setAttribute(Constants.VALIDATION_MESSAGE, "Selected Activity is active.");
        return mapping.findForward(forward);
      }
      try
      {
        ManageUserDBHelper.deleteActivity(activityForm.getId());
        searchedActivityList.remove(activityForm);
        httpSession.setAttribute(Constants.SEARCHED_ACTIVITIES, searchedActivityList);
      }
      catch (Exception e)
      {
        request.setAttribute(Constants.VALIDATION_MESSAGE, e.getLocalizedMessage());
      }
    }
    else if (operation.equalsIgnoreCase("Add"))
    {
      activityForm = new ActivityForm();
      activityForm.setActive(true);
      forward = "add";
      request.setAttribute("activityForm", activityForm);
    }
    httpSession.setAttribute(Constants.ROLE_ACTIVITIES, ManageUserDBHelper.getAllRoleActivities());
    return mapping.findForward(forward);

  }

}
