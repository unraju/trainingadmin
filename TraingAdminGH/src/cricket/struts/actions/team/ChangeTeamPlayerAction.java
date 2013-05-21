package cricket.struts.actions.team;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.util.CheckSessionValidate;
import common.util.Constants;
import common.util.RetriveContextData;

import cricket.hibernate.bf.player.Player;
import cricket.struts.actionforms.team.CoreTeamForm;
import cricket.struts.actionforms.team.PlayerForm;
import cricket.struts.actionforms.team.UserTeamForm;
import cricket.struts.helpers.team.ChnageTeamPlayerHelper;

public class ChangeTeamPlayerAction extends org.apache.struts.action.Action
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		CheckSessionValidate.isSessionValide(request, response);
		HttpSession httpSession = request.getSession();
		String operation = (String) request.getParameter("operation");
		String forward = "error";
		// the below teamForm user to populate Captain change flag only.
		PlayerForm tempForm = (PlayerForm) form;
		PlayerForm playerForm = (PlayerForm) httpSession.getAttribute(Constants.PLAYER_FORM);
		UserTeamForm userTeamForm = (UserTeamForm) httpSession.getAttribute(Constants.USER_TEAM);

		ServletContext ctx = getServlet().getServletContext();
		List<CoreTeamForm> coreteams = new RetriveContextData().getCoreTeamDetails(ctx);

		boolean selectedAsCaptain = false;
		if (!playerForm.isCaptain() && tempForm.isCaptain())
		{
			selectedAsCaptain = true;
		}

		// System.out.println("### selectedAsCaptain ####"+selectedAsCaptain);

		Set<String> playerIdList = new HashSet<String>();

		if (isTokenValid(request))
		{
			saveToken(request);

		}
		else
		{
			// errors.add("error.duplicaterequest", new
			// ActionMessage("error.duplicaterequest"));
			request.setAttribute(Constants.ERROR_MESSAGE, "Failed -  Duplicate data is submitting");
			// resetToken(request);
			playerForm = new PlayerForm();
			request.setAttribute(Constants.PLAYER_FORM, playerForm);
			return (mapping.getInputForward());
		}

		for (PlayerForm tempPlayerForm : userTeamForm.getPlayers())
		{
			playerIdList.add(tempPlayerForm.getId().toString());
		}

		if (operation.equalsIgnoreCase("Confirm"))
		{
			PlayerForm newPlayerForm = null;
			String palyerId = (String) request.getParameter("newPlayer");
			if (palyerId != null && palyerId.startsWith("Please") && !selectedAsCaptain)
			{
				forward = "errorMessage";
				request.setAttribute(Constants.ERROR_MESSAGE,
						"Select new player (or) make this as player captain (or) do both.");
				return mapping.findForward(forward);
			}
			if (playerIdList.contains(palyerId))// && !playerForm.isCaptain())
			{
				forward = "errorMessage";
				request.setAttribute(Constants.ERROR_MESSAGE,
						"Selected Player is already part of your squad, Select another one .");
				return mapping.findForward(forward);
			}
			// to check selected player is inactive
			if (palyerId != null && !palyerId.startsWith("Please"))
			{
				Player player = new ChnageTeamPlayerHelper().findPlayerById(Long.parseLong(palyerId));
				if (!player.isActive())
				{
					forward = "errorMessage";
					request.setAttribute(Constants.ERROR_MESSAGE,
							"Selected Player is ruled out of series. Select another player.");
					return mapping.findForward(forward);
				}

			}

			if (palyerId != null && !palyerId.startsWith("Please"))
			{
				for (CoreTeamForm coreTeamForm : coreteams)
				{
					for (PlayerForm tempPlayer : coreTeamForm.getPlayers())
					{

						if (palyerId.equals(tempPlayer.getCoreTeamPlayerId().toString()))
						{
							newPlayerForm = tempPlayer;
						}
					}
				}
			}

			// ManageTeamDBHelper.changeTeamPlayer(userTeamForm, playerForm,
			// newPlayerForm);
			new ChnageTeamPlayerHelper().changeTeamPlayer(userTeamForm, playerForm, newPlayerForm, selectedAsCaptain);
			// HibernateUtil.commitTransaction();
			// HibernateUtil.beginTransaction();
			// User user = (User) httpSession.getAttribute(Constants.USER);
			// userTeamForm = new ManageTeamDBHelper().getUserTeamDetails(user);
			// userTeamForm.setCoreTeams(coreteams);
			// System.out.println("### userTeamForm ###"+userTeamForm);
			// httpSession.setAttribute("userTeamForm", userTeamForm);
			// request.setAttribute("userTeamForm", userTeamForm);
			forward = "success";
		}
		else if (operation.equalsIgnoreCase("Cancel"))
		{
			forward = "cancel";
		}
		request.setAttribute(Constants.FORWARD_ACTION, "manageUserTeam.do?menu=109&submenu=6");
		return mapping.findForward(forward);

	}

}
