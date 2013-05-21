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

public class AddActivityDetailsAction extends org.apache.struts.action.Action
{

  @Override
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
  {

    CheckSessionValidate.isSessionValide(request, response);
    HttpSession httpSession = request.getSession();
    String operation = request.getParameter("operation");
    String forward = "error";
    ActivityForm activityForm = (ActivityForm) form;
    if (operation.equalsIgnoreCase("Save"))
    {

      ManageUserDBHelper.addUpdateActivity(activityForm);
      forward = "sucess";
      List<ActivityForm> searchedActivityList = (ArrayList<ActivityForm>) httpSession.getAttribute(common.util.Constants.SEARCHED_ACTIVITIES);
      searchedActivityList.add(activityForm);
      httpSession.setAttribute(Constants.SEARCHED_ROLES, searchedActivityList);
    }
    else if (operation.equalsIgnoreCase("Cancel"))
    {
      forward = "cancel";
    }
    return mapping.findForward(forward);

  }

}
