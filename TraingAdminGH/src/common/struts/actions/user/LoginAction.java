package common.struts.actions.user;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.actionforms.user.ActivityForm;
import common.struts.actionforms.user.LoginForm;
import common.struts.actionforms.user.UserForm;
import common.struts.actionforms.user.UserRoleForm;
import common.struts.helpers.user.LoginDBHelper;
import common.util.Constants;
import common.util.RetriveContextData;

public class LoginAction extends Action
{

  @Override
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
  {
  HttpSession httpSession = request.getSession();

  LoginForm loginForm = (LoginForm) form;
  String forward = "error";
  try
  {

    UserForm loginUser = LoginDBHelper.compareLoginDetails(loginForm);

    if (loginUser != null)
    {
      if (loginUser.getPwd().equals(loginForm.getPwd()))
      {
        if (loginUser.isActiveUser())
        {
          forward = "success";
          httpSession.setMaxInactiveInterval(900);
          httpSession.setAttribute(Constants.USER, loginUser);

         /* if (loginUser.isMailFailed())
          {
            httpSession.setAttribute(Constants.ERROR_MESSAGE, "Please update valid email id(using Update Profile link).");
          }*/
          ServletContext context = this.getServlet().getServletContext();
          List<UserRoleForm> roleForms = new RetriveContextData().getUserNotAssociatedRoles(context);
          // List<UserRoleForm> loggedInUserRoleForms = new
          // ArrayList<UserRoleForm>();
          for (UserRoleForm roleForm : roleForms)
          {
            if (!roleForm.isUserAssociated() && roleForm.isActive())
            {
              loginUser.getUserRoles().add(roleForm);
            }
          }
          // Collections.sort(loginUser.getUserRoles(), new
          // UserRoleFormComparator<UserRoleForm>());
          // loginUser.setUserRoles(loggedInUserRoleForms);
          // LoginDBHelper.setLogedInUserRoles(loginUser);
        }
        else
        {
          forward = "errorMessage";
          request.setAttribute(Constants.ERROR_MESSAGE, "User was decativated. Don't try again. ");
        }

        List<UserRoleForm> userRoleForms = loginUser.getUserRoles();
        String forwardUrl = null;
        if (userRoleForms != null && userRoleForms.size() > 0)
        {
          for (UserRoleForm userRoleForm : userRoleForms)
          {
            if (userRoleForm != null) // && userRoleForm.isUserAssociated()) //
                                      // this comment for time being until next
                                      // series launched
            {
              List<ActivityForm> activityForms = userRoleForm.getActivities();
              if (activityForms != null && activityForms.size() > 0)
              {
                ActivityForm activityForm = activityForms.get(0);
                forwardUrl = activityForm.getActivityUrl() + ".do?menu=" + userRoleForm.getId() + "&submenu=" + activityForm.getId();
              }
              break;
            }
          }
        }
        request.setAttribute(Constants.FORWARD_ACTION, forwardUrl);
      }
      else
      {
        forward = "errorMessage";
        request.setAttribute(Constants.ERROR_MESSAGE, "Password is not matched, Try again.");
      }
    }
    else
    {
      forward = "errorMessage";
      request.setAttribute(Constants.ERROR_MESSAGE, "Invalid User Name, Please check it.");
      return mapping.findForward(forward);
    }
  }
  catch (Exception e)
  {
    e.printStackTrace();
    request.setAttribute("error", e.getLocalizedMessage());
    forward = "errorMessage";
  }

  return mapping.findForward(forward);
  }

}
