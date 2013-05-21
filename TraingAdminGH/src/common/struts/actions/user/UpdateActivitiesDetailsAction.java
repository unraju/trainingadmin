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

public class UpdateActivitiesDetailsAction extends org.apache.struts.action.Action
{

  @Override
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
  {

    CheckSessionValidate.isSessionValide(request, response);
    HttpSession httpSession = request.getSession();
    String operation = request.getParameter("operation");
    String forward = "error";
    if (operation.equalsIgnoreCase("Update"))
    {
      List<ActivityForm> activityList = (ArrayList<ActivityForm>) httpSession.getAttribute(Constants.SEARCHED_ACTIVITIES);
      for (ActivityForm activityForm : activityList)
      {

        String activity = (String) request.getParameter(activityForm.getId().toString() + "X1");
        String activityUrl = (String) request.getParameter(activityForm.getId().toString() + "X2");
        String priority = (String) request.getParameter(activityForm.getId().toString() + "X3");
        String active = (String) request.getParameter(activityForm.getId().toString() + "X4");
        activityForm.setActivity(activity);
        activityForm.setActivityUrl(activityUrl);
        activityForm.setPriority(Long.parseLong(priority));
        if (active.equals("true"))
        {
          activityForm.setActive(true);
        }
        else
        {
          activityForm.setActive(false);
        }
      }
      ManageUserDBHelper.updateActivities(activityList);
      forward = "sucess";
      httpSession.setAttribute(Constants.SEARCHED_ACTIVITIES, activityList);
      httpSession.setAttribute(Constants.ROLE_ACTIVITIES, activityList);
    }
    else if (operation.equalsIgnoreCase("Cancel"))
    {
      forward = "cancel";
    }
    
    return mapping.findForward(forward);
  }

}
