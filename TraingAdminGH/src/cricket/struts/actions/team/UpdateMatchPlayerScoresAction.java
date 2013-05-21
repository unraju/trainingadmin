package cricket.struts.actions.team;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.util.CheckSessionValidate;
import common.util.Constants;

import cricket.struts.actionforms.team.PlayerMatchScoresForm;
import cricket.struts.actionforms.team.TeamSheduleForm;
import cricket.struts.helpers.team.ManageTeamDBHelper;

public class UpdateMatchPlayerScoresAction extends org.apache.struts.action.Action
{

  @Override
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
  {
    CheckSessionValidate.isSessionValide(request, response);
    String operation = request.getParameter("operation");
    HttpSession httpSession = request.getSession();
    TeamSheduleForm teamSheduleForm = (TeamSheduleForm) form;

    String forward = "error";
    if (operation.equalsIgnoreCase("Cancel"))
    {
      forward = "cancel";
      return mapping.findForward(forward);
    }
    List<PlayerMatchScoresForm> updatedPlayerMatchScores = new ArrayList<PlayerMatchScoresForm>();
    //TeamSheduleForm teamSheduleForm = (TeamSheduleForm) httpSession.getAttribute(Constants.MATCH_DETAILS);
    List<PlayerMatchScoresForm> firstTeamPlayerMatchScores = (ArrayList<PlayerMatchScoresForm>) httpSession.getAttribute(Constants.FIRST_TEAM_PLATER_MATCH_SCORES);
    for (PlayerMatchScoresForm playerMatchScoreForm :firstTeamPlayerMatchScores)
    {
        String runs = request.getParameter(playerMatchScoreForm.getPlayerId() + "M2");
        String wickets = request.getParameter(playerMatchScoreForm.getPlayerId() + "M3");
        String catches = request.getParameter(playerMatchScoreForm.getPlayerId() + "M4");
        String balls = request.getParameter(playerMatchScoreForm.getPlayerId() + "M5");
        String fours = request.getParameter(playerMatchScoreForm.getPlayerId() + "M6");
        String sixers = request.getParameter(playerMatchScoreForm.getPlayerId() + "M7");
        playerMatchScoreForm.setRuns(runs);
        playerMatchScoreForm.setWickets(wickets);
        playerMatchScoreForm.setCatches(catches);
        playerMatchScoreForm.setBalls(balls);
        playerMatchScoreForm.setFours(fours);
        playerMatchScoreForm.setSixers(sixers);
      }
 
  List<PlayerMatchScoresForm> secondTeamPlayerMatchScores = (ArrayList<PlayerMatchScoresForm>) httpSession.getAttribute(Constants.SECOND_TEAM_PLATER_MATCH_SCORES);
  for (PlayerMatchScoresForm playerMatchScoreForm :secondTeamPlayerMatchScores)
  {
        String runs = request.getParameter(playerMatchScoreForm.getPlayerId() + "M2");
        String wickets = request.getParameter(playerMatchScoreForm.getPlayerId() + "M3");
        String catches = request.getParameter(playerMatchScoreForm.getPlayerId() + "M4");
        String balls = request.getParameter(playerMatchScoreForm.getPlayerId() + "M5");
        String fours = request.getParameter(playerMatchScoreForm.getPlayerId() + "M6");
        String sixers = request.getParameter(playerMatchScoreForm.getPlayerId() + "M7");
        playerMatchScoreForm.setRuns(runs);
        playerMatchScoreForm.setWickets(wickets);
        playerMatchScoreForm.setCatches(catches);
        playerMatchScoreForm.setBalls(balls);
        playerMatchScoreForm.setFours(fours);
        playerMatchScoreForm.setSixers(sixers);
    }
  updatedPlayerMatchScores.addAll(firstTeamPlayerMatchScores);
  updatedPlayerMatchScores.addAll(secondTeamPlayerMatchScores);
    new ManageTeamDBHelper().updateTeamPlayerScores(updatedPlayerMatchScores,teamSheduleForm);
    forward = "success";
    return mapping.findForward(forward);
  }
}
