package cricket.struts.actions.team;

import java.util.ArrayList;
import java.util.List;

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
import common.util.UserUtil;

import cricket.struts.actionforms.common.SeriesForm;
import cricket.struts.actionforms.team.CoreTeamForm;
import cricket.struts.actionforms.team.PlayerForm;
import cricket.struts.helpers.team.ManageTeamDBHelper;

public class UpdateCoreTeamDetailsAction extends org.apache.struts.action.Action
{

  @Override
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
  {

    CheckSessionValidate.isSessionValide(request, response);
    ManageTeamDBHelper manageTeamDBHelper = new ManageTeamDBHelper();
    HttpSession httpSession = request.getSession();
    String operation = request.getParameter("operation");
    String forward = "error";
    CoreTeamForm coreTeamForm = (CoreTeamForm) form;
    boolean newTeam = true;
    ServletContext ctx = getServlet().getServletContext();
    SeriesForm seriesForm = new RetriveContextData().getCurrentSeriesForm();
    if (coreTeamForm.getId() != null && coreTeamForm.getId().intValue() != 0)
    {
      newTeam = false;
    }
    if (operation.equalsIgnoreCase("Save"))
    {
      if (newTeam)
      {
        coreTeamForm.createDummyPlayers(seriesForm.getPlayerInSquad());
      }
      CoreTeamForm tempTeamForm = (CoreTeamForm) httpSession.getAttribute(Constants.CORE_TEAM_FORM);
      List<PlayerForm> toBeUpdatedPlayers = new ArrayList<PlayerForm>();
      for (PlayerForm playerForm : tempTeamForm.getPlayers())
    {
      String playerId = (String) request.getParameter(playerForm.getId().toString() + "P1");
      /*
       * String country = (String)
       * request.getParameter(playerForm.getId().toString() + "P2"); String
       * Skill = (String) request.getParameter(playerForm.getId().toString() +
       * "P3");
       */
      String active = (String) request.getParameter(playerForm.getId().toString() + "P4");
      String captain = (String) request.getParameter(playerForm.getId().toString() + "P5");
      if (playerId != null && !playerId.trim().startsWith("P"))
      {
        playerForm.setCoreTeamPlayerId(Long.parseLong(playerId));
        // playerForm.setSkill(Skill);
        // playerForm.setCountry(country);
        if (newTeam)
        {
          tempTeamForm.setId(null);
        }
        if (active != null && active.equals("true"))
        {
          playerForm.setActive(true);
        }
        else
        {
          playerForm.setActive(false);
        }
        if (captain != null && captain.equals("true"))
        {
          playerForm.setCaptain(true);
        }
        else
        {
          playerForm.setCaptain(false);
        }
        if (playerForm.getId().intValue() >= 1000)
        {
          playerForm.setId(null);
        }
        toBeUpdatedPlayers.add(playerForm);
      }
    }
      coreTeamForm.setPlayers(toBeUpdatedPlayers);
      manageTeamDBHelper.saveCoreTeamDetails(coreTeamForm);
      //HibernateUtil.commitTransaction();
      httpSession.setAttribute(Constants.SEARCHED_CORE_TEAMS, new ManageTeamDBHelper().getAllCoreTeamDetails(UserUtil.getSeries()));
      ctx.setAttribute(Constants.SEARCHED_CORE_TEAMS, new ManageTeamDBHelper().getAllCoreTeamDetails(UserUtil.getSeries()));
      forward = "sucess";
    }
    else if (operation.equalsIgnoreCase("Cancel"))
    {
      forward = "cancel";
    }
    else if (operation.equalsIgnoreCase("Back"))
    {
      forward = "back";
    }
    return mapping.findForward(forward);

  }

}
