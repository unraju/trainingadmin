package common.struts.actions.user;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.actionforms.user.UserForm;
import common.struts.actionforms.user.UserRoleForm;
import common.struts.helpers.user.ManageUserDBHelper;
import common.util.Constants;

public class PopulateUserDetailsAction extends org.apache.struts.action.Action
{

  @Override
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
  {
    //CheckSessionValidate.isSessionValide(request, response);
    String operation = request.getParameter("operation");
    // AddExpActionForm addExpActionForm = (AddExpActionForm)form;
    long selectedUserId = 0;
    if(request.getParameter("id") !=  null)
    {
     selectedUserId = Integer.parseInt(request.getParameter("id"));
    }
    String forward = "error";
   /* if (operation.equalsIgnoreCase("Update") || operation.equalsIgnoreCase("delete"))
    {
      if (selectedUserId == 0)
      {
        request.setAttribute(Constants.VALIDATION_MESSAGE,  "Select User to be Deleted.");
        forward = "validation";
        return mapping.findForward(forward);
      }
    }*/

    HttpSession httpSession = request.getSession();
    UserForm userForm = null;
    List<UserForm> searchedUserList = (ArrayList<UserForm>) httpSession.getAttribute(Constants.SEARCHED_USERS);
    for (UserForm tempUser : searchedUserList)
    {
      if (selectedUserId == tempUser.getId().longValue())
      {
        userForm = tempUser;
        break;
      }
    }

    if (operation.equalsIgnoreCase("Update"))
    {
      forward = "update";
      userForm.setAllUserRoles(ManageUserDBHelper.getAllUserRoles());
      for (UserRoleForm roleForm : userForm.getUserRoles())
      {
        for (UserRoleForm roleForm1 : userForm.getAllUserRoles())
        {
          if (roleForm.getId().longValue() == roleForm1.getId().longValue())
          {
            roleForm1.setChecked(true);
          }
        }
      }
      httpSession.setAttribute("userForm", userForm);
      request.setAttribute("userForm", userForm);
      // HibernateDBHelper.updateExpDetails(form,
      // request.getSession().getAttribute(Constants.USER));
    }
    else if (operation.equalsIgnoreCase("delete"))
    {
      forward = "delete";
      ManageUserDBHelper.deleteUser(userForm.getId());
      searchedUserList.remove(userForm);
      httpSession.setAttribute(Constants.SEARCHED_USERS, searchedUserList);
    }

    return mapping.findForward(forward);

  }

}
