package cricket.struts.actions.team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.util.CheckSessionValidate;
import common.util.Constants;
import common.util.DateUtil;

import cricket.hibernate.bf.player.PlayerMatchOrderComparator;
import cricket.hibernate.bf.player.PlayerMatchScores;
import cricket.struts.actionforms.team.PlayerForm;
import cricket.struts.actionforms.team.PlayerMatchScoresForm;
import cricket.struts.actionforms.team.TeamSheduleForm;
import cricket.struts.helpers.team.ManageTeamDBHelper;

public class PopulateSelectedMatchPlayersAction extends org.apache.struts.action.Action
{

  @Override
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
  {
    CheckSessionValidate.isSessionValide(request, response);
    String operation = request.getParameter("operation");
    String forward = "error";
    HttpSession httpSession = request.getSession();
    if(operation.equalsIgnoreCase("Cancel"))
    {
      forward ="cancel";
      return mapping.findForward(forward);
    }
    TeamSheduleForm teamSheduleForm = (TeamSheduleForm) httpSession.getAttribute(Constants.MATCH_DETAILS);
    Date date =DateUtil.getDateFromStringFormatDATE_TIME(teamSheduleForm.getDisplayDate());
    int minutes=date.getMinutes();
    date.setMinutes(minutes-270);
   
    List<PlayerMatchScoresForm> firstTeamPlayerMatchScores =  new ArrayList<PlayerMatchScoresForm>();
    for(PlayerForm playerForm:teamSheduleForm.getFirstTeamForm().getPlayers())
  {
    String selected = request.getParameter(playerForm.getCoreTeamPlayerId() + "M1");
    String order = request.getParameter(playerForm.getCoreTeamPlayerId() + "M2");
    if (selected != null && selected.equalsIgnoreCase("Selected"))
    {
      playerForm.setSelected(true);
      PlayerMatchScores playerScores = new ManageTeamDBHelper().findPlayerMatchScore(teamSheduleForm.getId(), playerForm.getCoreTeamPlayerId());
      if (playerScores == null)
      {
        playerScores = new PlayerMatchScores();
        playerScores.setPlayerId(playerForm.getCoreTeamPlayerId());
        playerScores.setPlayerName(playerForm.getName());
        playerScores.setMatchId(teamSheduleForm.getId());
        playerScores.setSeriesId(Long.parseLong(teamSheduleForm.getSeriesId()));
       
        //playerScores.setDate(DateUtil.getDateFromStringFormatDATE_TIME(teamSheduleForm.getDisplayDate()));
       }
      playerScores.setDate(date);
      if(order != null && !order.trim().equals(""))
      {
        playerScores.setOrder(Long.parseLong(order));
      }
      firstTeamPlayerMatchScores.add(playerScores.getActionForm());
      
    }
    else
    {
      playerForm.setSelected(false);
    }
  }
    Collections.sort(firstTeamPlayerMatchScores, new PlayerMatchOrderComparator());
    httpSession.setAttribute(Constants.FIRST_TEAM_PLATER_MATCH_SCORES,firstTeamPlayerMatchScores);
    List<PlayerMatchScoresForm> secondTeamPlayerMatchScores =  new ArrayList<PlayerMatchScoresForm>();
    for(PlayerForm playerForm:teamSheduleForm.getSecondTeamForm().getPlayers())
  {
    String selected = request.getParameter(playerForm.getCoreTeamPlayerId() + "M1");
    String order = request.getParameter(playerForm.getCoreTeamPlayerId() + "M2");
    if (selected != null && selected.equalsIgnoreCase("Selected"))
    {
      playerForm.setSelected(true);
      PlayerMatchScores playerScores = new ManageTeamDBHelper().findPlayerMatchScore(teamSheduleForm.getId(), playerForm.getCoreTeamPlayerId());
      if (playerScores == null)
      {
        playerScores = new PlayerMatchScores();
        playerScores.setPlayerId(playerForm.getCoreTeamPlayerId());
        playerScores.setPlayerName(playerForm.getName());
        playerScores.setMatchId(teamSheduleForm.getId());
        playerScores.setSeriesId(Long.parseLong(teamSheduleForm.getSeriesId()));
        playerScores.setDate(DateUtil.getDateFromStringFormatDATE_TIME(teamSheduleForm.getDisplayDate()));
       }
      if(order != null && !order.trim().equals(""))
      {
        playerScores.setOrder(Long.parseLong(order));
      }
      playerScores.setDate(date);
      secondTeamPlayerMatchScores.add(playerScores.getActionForm());
    
    }
    else
    {
      playerForm.setSelected(false);
    }
  }
    Collections.sort(secondTeamPlayerMatchScores, new PlayerMatchOrderComparator());
    httpSession.setAttribute(Constants.SECOND_TEAM_PLATER_MATCH_SCORES,secondTeamPlayerMatchScores);
    request.setAttribute("teamSheduleForm", teamSheduleForm);
    forward = "success";
    return mapping.findForward(forward);
  }
}
