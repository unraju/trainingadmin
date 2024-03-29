package cricket.struts.actions.team;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.util.Constants;

import cricket.hibernate.bf.team.UserTeam;
import cricket.struts.helpers.team.ManageTeamDBHelper;

public class ViewTeamsScoreAction extends Action
{

  @Override
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
  {
    HttpSession session = request.getSession();
    ManageTeamDBHelper manageTeamDBHelper = new ManageTeamDBHelper();
    String forward = "success";
    try
    {
      List<UserTeam> userTeams = manageTeamDBHelper.getAllUserTeams();
      if(userTeams == null)
      {
        userTeams = new ArrayList<UserTeam>();
      }
      session.setAttribute(Constants.USER_TEAMS_SCORES, userTeams);
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
