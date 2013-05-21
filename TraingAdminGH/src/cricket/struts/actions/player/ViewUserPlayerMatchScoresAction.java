package cricket.struts.actions.player;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.util.Constants;

import cricket.hibernate.bf.player.PlayerMatchScores;
import cricket.struts.actionforms.team.PlayerMatchScoresForm;
import cricket.struts.helpers.team.ManageTeamDBHelper;

public class ViewUserPlayerMatchScoresAction extends Action
{
  @Override
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
  {
  String playerId = request.getParameter("id");
  String back = request.getParameter("back");
  StringBuffer playerName = new StringBuffer();
  playerName.append("");
  List<PlayerMatchScoresForm> playerMatchScoresForms = new ArrayList<PlayerMatchScoresForm>();
  if (playerId != null && !playerId.trim().equals(""))
  {
    List<PlayerMatchScores> playerMatchScores = new ManageTeamDBHelper().getUserPlayerMatchScores(Long.parseLong(playerId),playerName);
    if (playerMatchScores != null && playerMatchScores.size() > 0)
    {
      for (PlayerMatchScores matchScores : playerMatchScores)
        playerMatchScoresForms.add(matchScores.getActionForm());
    }
  }

 /* if(playerMatchScoresForms.size() >0)
  {
    playerName = playerMatchScoresForms.get(0).getPlayerName();
  } else
  {
    playerName = new ManageTeamDBHelper().findPlayerById(Long.parseLong(playerId)).getName();
  }*/
  request.setAttribute(Constants.PLAYER_MATCH_SCORE_FORMS, playerMatchScoresForms);
  request.setAttribute(Constants.BACK_REF, back);
  request.setAttribute(Constants.PLAYER_NAME,playerName.toString());

 return mapping.findForward("success");
  }
}
