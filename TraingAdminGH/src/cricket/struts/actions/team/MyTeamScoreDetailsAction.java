package cricket.struts.actions.team;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.actionforms.user.UserForm;
import common.util.CheckSessionValidate;
import common.util.Constants;

import cricket.struts.actionforms.team.UserTeamForm;
import cricket.struts.helpers.team.ManageTeamDBHelper;

public class MyTeamScoreDetailsAction extends org.apache.struts.action.Action
{

  @Override
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
  {
    CheckSessionValidate.isSessionValide(request, response);
    HttpSession session = request.getSession();
    ManageTeamDBHelper manageTeamDBHelper=new ManageTeamDBHelper();
    String forward = "success";
    try
    {
      UserForm user = (UserForm) session.getAttribute(Constants.USER);
      if (user == null)
      {
        forward = "invalidSession";
        return mapping.findForward(forward);
      }

      UserTeamForm userTeamForm = manageTeamDBHelper.getAllUserTeamPlayers(user.getId());
      if (userTeamForm == null || userTeamForm.getPlayers() == null || !(userTeamForm.getPlayers() != null && userTeamForm.getPlayers().size() > 0))
      {
        if (userTeamForm == null)
        {
          userTeamForm = new UserTeamForm();
        }
        userTeamForm.createDummyPlayers();
        forward = "createTeam";
      }
      //userTeamForm.setCoreTeams((ArrayList<CoreTeamForm>) manageTeamDBHelper.getAllCoreTeamDetails());
      session.setAttribute("userTeamForm", userTeamForm);
      //request.setAttribute("userTeamForm", userTeamForm);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      forward = "error";
      request.setAttribute("error", e.getLocalizedMessage());
    }
    return mapping.findForward(forward);
  }

}
