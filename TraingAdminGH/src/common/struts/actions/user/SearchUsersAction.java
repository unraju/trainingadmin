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

import common.hibernate.bf.user.User;
import common.struts.actionforms.user.SearchUserForm;
import common.struts.actionforms.user.UserForm;
import common.struts.helpers.user.ManageUserDBHelper;
import common.util.CheckSessionValidate;
import common.util.Constants;

public class SearchUsersAction extends Action
{

  @Override
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
  {

    CheckSessionValidate.isSessionValide(request, response);
    HttpSession session = request.getSession();
    session.removeAttribute(Constants.SEARCHED_USERS);
    SearchUserForm searchUserForm = (SearchUserForm) form;
    String forward = "error";
    try
    {
      List<User> users = ManageUserDBHelper.searchUsers(searchUserForm);
      List<UserForm> userList = new ArrayList<UserForm>();
      for (User user : users)
      {
        userList.add(user.getUserForm());
      }
      session.setAttribute(Constants.SEARCHED_USERS, userList);
      forward = "success";
    }
    catch (Exception e)
    {
      e.printStackTrace();
      request.setAttribute("error", e.getLocalizedMessage());
    }
    return mapping.findForward(forward);
  }

}
