package common.struts.actions.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.actionforms.user.UserForm;
import common.struts.helpers.user.LoginDBHelper;
import common.util.Constants;

import cricket.struts.actionforms.team.UserTeamForm;
import cricket.struts.helpers.team.ManageTeamDBHelper;

public class AgreementAction extends Action
{

  @Override
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
  {
    HttpSession httpSession = request.getSession();
    String accepted = (String) request.getAttribute("agreed");
    String forward = "error";
    UserForm user=(UserForm) httpSession.getAttribute(Constants.USER);
    if (accepted != null && accepted.equals("Aceepted"))
    {
      LoginDBHelper.updateUserforAcceptence(user);
      UserTeamForm userTeamForm = new ManageTeamDBHelper().getUserTeamDetails(user.getId());
      if (userTeamForm == null || userTeamForm.getPlayers() == null || !(userTeamForm.getPlayers() != null && userTeamForm.getPlayers().size() > 0))
      {
        if (userTeamForm == null)
        {
          userTeamForm = new UserTeamForm();
        }
        userTeamForm.createDummyPlayers();
        forward = "createTeam";
      }
      else
      {
        forward = "manageTeam";
      }

      forward = "createTeam";
    }
    else
    {
      forward = "notAccepted";
    }

    return mapping.findForward(forward);
  }

}
