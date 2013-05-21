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
import common.util.UserUtil;

import cricket.hibernate.bf.player.PlayerMatchScores;
import cricket.struts.actionforms.team.PlayerMatchScoresForm;
import cricket.struts.helpers.team.ManageTeamDBHelper;

public class ViewPlayerMatchScoresAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String playerId = request.getParameter("id");
		String back = request.getParameter("back");

		List<PlayerMatchScoresForm> playerMatchScoresForms = new ArrayList<PlayerMatchScoresForm>();
		if (playerId != null && !playerId.trim().equals("")) {
			List<PlayerMatchScores> playerMatchScores = new ManageTeamDBHelper().findPlayerSeriesScore(UserUtil.getSeries(),
					Long.parseLong(playerId));
			if (playerMatchScores != null && playerMatchScores.size() > 0) {
				for (PlayerMatchScores matchScores : playerMatchScores)
					playerMatchScoresForms.add(matchScores.getActionForm());
			}
		}
		String playerName = "";
		if (playerMatchScoresForms.size() > 0) {
			playerName = playerMatchScoresForms.get(0).getPlayerName();
		} else {
			playerName = new ManageTeamDBHelper().findPlayerById(Long.parseLong(playerId)).getName();
		}
		request.setAttribute(Constants.PLAYER_MATCH_SCORE_FORMS, playerMatchScoresForms);
		request.setAttribute(Constants.BACK_REF, back);
		request.setAttribute(Constants.PLAYER_NAME, playerName);

		return mapping.findForward("success");
	}
}
