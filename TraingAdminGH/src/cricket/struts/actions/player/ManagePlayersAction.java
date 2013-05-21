package cricket.struts.actions.player;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.util.CheckSessionValidate;
import common.util.Constants;
import common.util.RetriveContextData;

import cricket.struts.actionforms.common.SeriesTypeForm;
import cricket.struts.actionforms.team.PlayerForm;
import cricket.struts.helpers.player.ManagePlayerDBHelper;

public class ManagePlayersAction extends Action
{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		CheckSessionValidate.isSessionValide(request, response);
		PlayerForm playerForm = (PlayerForm) form;
		String operation = request.getParameter("operation");
		String forward = "success";
		HttpSession session = request.getSession();
		ServletContext ctx = getServlet().getServletContext();
		List<SeriesTypeForm> seriesTypeForms = new RetriveContextData().getSeriesTypeData();
		try
		{
			ManagePlayerDBHelper helper = new ManagePlayerDBHelper();
			// helper.searchPlayer(playerForm);
			if (operation.equals(Constants.SEARCH_BTN_VALUE))
			{
				session.setAttribute(Constants.SEARCHED_PLAYERS, helper.searchPlayer(playerForm));
				forward = "populateSearchedPlayers";
				// request.setAttribute("success", "Detals added Sucessfully");
			}
			else if (operation.equals(Constants.ADD_BTN_VALUE))
			{
				forward = "addUpdatePlayer";
				playerForm = new PlayerForm();
				playerForm.addDefaultScores(seriesTypeForms);
				request.setAttribute(Constants.PLAYER_FORM, playerForm);
			}
			else if (operation.equals(Constants.UPDATE_BTN_VALUE))
			{
				String playerId = request.getParameter("selectedId");
				if (playerId != null && !playerId.trim().equals(""))
				{
					forward = "addUpdatePlayer";
					playerForm = helper.findPlayerById(Long.parseLong(playerId)).getActionFormForManagePlayer();
					playerForm.addDefaultScores(seriesTypeForms);
					request.setAttribute(Constants.PLAYER_FORM, playerForm);
				}
				else
				{
					forward = "populateSearchedPlayers";
					request.setAttribute(Constants.ERROR_MESSAGE_2, "Pelase Selete the Player");
				}
			}
			else if (operation.equals(Constants.DELETE_BTN_VALUE))
			{
				request.setAttribute(Constants.ERROR_MESSAGE_2, "Delete Player is not allowed for time being.");
				forward = "populateSearchedPlayers";
			}
			else if (operation.equals(Constants.CANCEL_BTN_VALUE))
			{
				forward = "cancel";
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			request.setAttribute(Constants.ERROR_MESSAGE, e.getLocalizedMessage());
			forward = "error";
			request.setAttribute("success", "");
		}

		return mapping.findForward(forward);

	}

}
