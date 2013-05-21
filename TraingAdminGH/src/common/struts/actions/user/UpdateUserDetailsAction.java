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

public class UpdateUserDetailsAction extends org.apache.struts.action.Action
{

  @Override
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
  {
    common.util.CheckSessionValidate.isSessionValide(request, response);
    HttpSession httpSession = request.getSession();
    String operation = request.getParameter("operation");
    // AddExpActionForm addExpActionForm = (AddExpActionForm)form;
    String forward = "error";
    UserForm userForm = (UserForm) form;
    if (operation.equalsIgnoreCase("Update"))
    {
      List<UserRoleForm> userRoleList = new ArrayList<UserRoleForm>();
      for (UserRoleForm roleForm : ManageUserDBHelper.getAllUserRoles())
      {
        String value = (String) request.getParameter(roleForm.getId().toString());
        if (value != null && value.equals("Selected"))
        {
          userRoleList.add(roleForm);
        }
      }
      userForm.setUserRoles(userRoleList);
      ManageUserDBHelper.updateUserRoles(userForm);
      forward = "sucess";
      List<UserForm> searchedUserList = (ArrayList<UserForm>) httpSession.getAttribute(Constants.SEARCHED_USERS);
      for (UserForm userForm2 : searchedUserList)
      {
        if (userForm2.getId().intValue() == userForm.getId().intValue())
        {
          userForm2.setUserRoles(userForm.getUserRoles());
          userForm2.setActive(userForm.getActive());
        }
      }
      httpSession.setAttribute(Constants.SEARCHED_USERS, searchedUserList);
    }
    else if (operation.equalsIgnoreCase("Cancel"))
    {
      forward = "cancel";
    }
    return mapping.findForward(forward);

  }

}
