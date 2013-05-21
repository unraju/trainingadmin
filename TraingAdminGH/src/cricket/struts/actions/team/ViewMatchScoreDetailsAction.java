package cricket.struts.actions.team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.util.Constants;
import common.util.RetriveContextData;

import cricket.hibernate.bf.player.PlayerMatchOrderComparator;
import cricket.hibernate.bf.player.PlayerMatchScores;
import cricket.struts.actionforms.team.PlayerForm;
import cricket.struts.actionforms.team.PlayerMatchScoresForm;
import cricket.struts.actionforms.team.TeamSheduleForm;
import cricket.struts.helpers.team.ManageTeamDBHelper;

public class ViewMatchScoreDetailsAction extends org.apache.struts.action.Action
{

  @Override
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
  {
     String operation = request.getParameter("operation");
    ManageTeamDBHelper manageTeamDBHelper = new ManageTeamDBHelper();
    if (operation != null && operation.equalsIgnoreCase("Back"))
    {
      return mapping.findForward("back");
    }
    String id = request.getParameter("id");
    //HttpSession httpSession = request.getSession();
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
        request.setAttribute(Constants.VALIDATION_MESSAGE, "Select Match to view Scores.");
        forward = "validation";
        return mapping.findForward(forward);
      }*/
      for (TeamSheduleForm sheduleForm : teamSheduleForms)
      {
        if (selectedUserId == sheduleForm.getId().longValue())
        {
          teamSheduleForm =  new ManageTeamDBHelper().findTeamSheduleById(selectedUserId).getActionForm();;
          break;
        }
      }
      List<PlayerMatchScoresForm> firstTeamPlayerMatchScores =  new ArrayList<PlayerMatchScoresForm>();
      for (PlayerForm playerForm : teamSheduleForm.getFirstTeamForm().getPlayers())
      {
        PlayerMatchScores playerScores = manageTeamDBHelper.findPlayerMatchScore(teamSheduleForm.getId(), playerForm.getCoreTeamPlayerId());
        if (playerScores != null)
        {
          /*playerForm.setSelected(true);
          if (playerScores.getRuns() != null) playerForm.setRuns(playerScores.getRuns().toString());
          if (playerScores.getWickets() != null) playerForm.setWickets(playerScores.getWickets().toString());
          if (playerScores.getCatches() != null) playerForm.setCatches(playerScores.getCatches().toString());
          if (playerScores.getBalls() != null) playerForm.setBalls(playerScores.getBalls().toString());
          if (playerScores.getScore() != null) playerForm.setScore(playerScores.getScore().toString());*/
          firstTeamPlayerMatchScores.add(playerScores.getActionForm());

        }
       }
      Collections.sort(firstTeamPlayerMatchScores, new PlayerMatchOrderComparator());
      request.setAttribute(Constants.FIRST_TEAM_PLATER_MATCH_SCORES,firstTeamPlayerMatchScores);

      List<PlayerMatchScoresForm> secondTeamPlayerMatchScores =  new ArrayList<PlayerMatchScoresForm>();
      for (PlayerForm playerForm : teamSheduleForm.getSecondTeamForm().getPlayers())
      {
        PlayerMatchScores playerScores = manageTeamDBHelper.findPlayerMatchScore(teamSheduleForm.getId(), playerForm.getCoreTeamPlayerId());
        if (playerScores != null)
        {
          /*playerForm.setSelected(true);
          if (playerScores.getRuns() != null) playerForm.setRuns(playerScores.getRuns().toString());
          if (playerScores.getWickets() != null) playerForm.setWickets(playerScores.getWickets().toString());
          if (playerScores.getCatches() != null) playerForm.setCatches(playerScores.getCatches().toString());
          if (playerScores.getBalls() != null) playerForm.setBalls(playerScores.getBalls().toString());
          if (playerScores.getScore() != null) playerForm.setScore(playerScores.getScore().toString());*/
          secondTeamPlayerMatchScores.add(playerScores.getActionForm());
        }
      }
      Collections.sort(secondTeamPlayerMatchScores, new PlayerMatchOrderComparator());
      request.setAttribute(Constants.SECOND_TEAM_PLATER_MATCH_SCORES,secondTeamPlayerMatchScores);
      request.setAttribute(Constants.MATCH_DETAILS, teamSheduleForm);
 
    forward = "success";
    return mapping.findForward(forward);
  }
}
