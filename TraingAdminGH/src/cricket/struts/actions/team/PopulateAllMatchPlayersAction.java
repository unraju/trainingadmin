package cricket.struts.actions.team;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.util.Constants;
import common.util.RetriveContextData;

import cricket.hibernate.bf.player.PlayerMatchScores;
import cricket.struts.actionforms.team.PlayerForm;
import cricket.struts.actionforms.team.TeamSheduleForm;
import cricket.struts.helpers.team.ManageTeamDBHelper;

public class PopulateAllMatchPlayersAction extends org.apache.struts.action.Action
{

  @Override
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
  {

  //String operation = request.getParameter("operation");
  String id = request.getParameter("id");
  HttpSession httpSession = request.getSession();
  long selectedUserId = 0;
  if (id != null)
  {
    selectedUserId = Integer.parseInt(request.getParameter("id"));
  }
  String forward = "error";
  TeamSheduleForm teamSheduleForm = null;
  ServletContext context = getServlet().getServletContext();
  List<TeamSheduleForm> teamSheduleForms = new RetriveContextData().getTeamShedule(context);

 
   /* if (selectedUserId == 0)
    {
      request.setAttribute(Constants.VALIDATION_MESSAGE, "Select Match to be Updated.");
      forward = "validation";
      return mapping.findForward(forward);
    }*/
    for (TeamSheduleForm sheduleForm : teamSheduleForms)
    {
      if (selectedUserId == sheduleForm.getId().longValue())
      {
        teamSheduleForm = new ManageTeamDBHelper().findTeamSheduleById(selectedUserId).getActionForm();
        break;
      }
    }
 
    for (PlayerForm playerForm : teamSheduleForm.getFirstTeamForm().getPlayers())
  {
    playerForm.setSelected(true);
    PlayerMatchScores playerScores = new ManageTeamDBHelper().findPlayerMatchScore(teamSheduleForm.getId(), playerForm.getCoreTeamPlayerId());
    if (playerScores == null)
    {
      playerForm.setSelected(false);
    }
    else
    {
      playerForm.setSelected(true);
    }
    if(playerScores != null && playerScores.getOrder() != null)
    playerForm.setOrder(playerScores.getOrder().toString());
  }
  for (PlayerForm playerForm : teamSheduleForm.getSecondTeamForm().getPlayers())
  {
    PlayerMatchScores playerScores = new ManageTeamDBHelper().findPlayerMatchScore(teamSheduleForm.getId(), playerForm.getCoreTeamPlayerId());
    if (playerScores == null)
    {
      playerForm.setSelected(false);
    }
    else
    {
      playerForm.setSelected(true);
    }
    if(playerScores != null && playerScores.getOrder() != null)
      playerForm.setOrder(playerScores.getOrder().toString());
  }

  httpSession.setAttribute(Constants.MATCH_DETAILS, teamSheduleForm);
  //System.out.println("### teamSheduleForm ###" + teamSheduleForm);
  forward = "success";
  return mapping.findForward(forward);
  }
}
