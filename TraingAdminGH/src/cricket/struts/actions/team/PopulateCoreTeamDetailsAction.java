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
import cricket.struts.helpers.player.ManagePlayerDBHelper;
import cricket.struts.helpers.team.ManageTeamDBHelper;

public class PopulateCoreTeamDetailsAction extends org.apache.struts.action.Action
{

  @Override
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
  {
    request.removeAttribute(Constants.VALIDATION_MESSAGE);
    ManageTeamDBHelper manageTeamDBHelper = new ManageTeamDBHelper();
    CheckSessionValidate.isSessionValide(request, response);
    String operation = request.getParameter("operation");
    String id = request.getParameter("id");
    HttpSession session = request.getSession();
    ServletContext ctx = getServlet().getServletContext();
    SeriesForm seriesForm = new RetriveContextData().getCurrentSeriesForm();
    long selectedUserId = 0;
    if (id != null)
    {
      selectedUserId = Integer.parseInt(request.getParameter("id"));
    }
    String forward = "error";
    if (operation.equalsIgnoreCase("Update") || operation.equalsIgnoreCase("Delete"))
    {
      session.setAttribute(Constants.SEARCHED_CORE_TEAMS, new ManageTeamDBHelper().getAllCoreTeamDetails(UserUtil.getSeries()));
      if (selectedUserId == 0)
      {
        request.setAttribute(Constants.VALIDATION_MESSAGE, "select record for doing edit/delete.");
        forward = "validation";
        return mapping.findForward(forward);
      }
    }
    if (operation.equalsIgnoreCase("View"))
    {
      if (selectedUserId == 0)
      {
        request.setAttribute(Constants.VALIDATION_MESSAGE, "Select team to view details.");
        forward = "viewValidation";
        return mapping.findForward(forward);
      }
    }
    HttpSession httpSession = request.getSession();
    CoreTeamForm coreTeamForm = new CoreTeamForm();
    List<CoreTeamForm> searchedCoreTeamList = (ArrayList<CoreTeamForm>) httpSession.getAttribute(Constants.SEARCHED_CORE_TEAMS);
    if(searchedCoreTeamList == null)
    {
      searchedCoreTeamList = new ArrayList<CoreTeamForm>();
    }
    for (CoreTeamForm tempForm : searchedCoreTeamList)
    {
      if (selectedUserId == tempForm.getId().longValue())
      {
        coreTeamForm = tempForm;
        break;
      }
    }

    if (operation.equalsIgnoreCase("Update"))
    {
      forward = "update";
      /*
       * if(coreTeamForm.getPlayers() == null || !(coreTeamForm.getPlayers() !=
       * null && coreTeamForm.getPlayers().size() >0)) {
       */
      coreTeamForm.createDummyPlayers(seriesForm.getPlayerInSquad());
      // }
      httpSession.setAttribute(Constants.CORE_TEAM_FORM, coreTeamForm);
      request.setAttribute(Constants.CORE_TEAM_FORM, coreTeamForm);
      session.setAttribute(Constants.SEARCHED_PLAYERS, ManagePlayerDBHelper.searchPlayerByCountry(coreTeamForm.getCountryId()));
      httpSession.setAttribute(Constants.OPERATION, "Update");
    }
    else if (operation.equalsIgnoreCase("View"))
    {
      forward = "View";
      /*
       * if(coreTeamForm.getPlayers() == null || !(coreTeamForm.getPlayers() !=
       * null && coreTeamForm.getPlayers().size() >0)) {
       */
      coreTeamForm.createDummyPlayers(seriesForm.getPlayerInSquad());
      // }
      httpSession.setAttribute("coreTeamForm", coreTeamForm);
      request.setAttribute("coreTeamForm", coreTeamForm);
     }
    else if (operation.equalsIgnoreCase("delete"))
    {
      forward = "delete";
      if (!coreTeamForm.isActive())
      {
        manageTeamDBHelper.deleteCoreTeam(coreTeamForm);
        httpSession.setAttribute(Constants.SEARCHED_CORE_TEAMS, manageTeamDBHelper.getAllCoreTeamDetails(UserUtil.getSeries()));
      }
      else
      {
        forward = "validation";
        request.setAttribute(Constants.VALIDATION_MESSAGE, "Selected Team is active.");
      }

    }
    else if (operation.equalsIgnoreCase("Add"))
    {
      forward = "add";
      coreTeamForm = new CoreTeamForm();
      coreTeamForm.setActive(true);
      coreTeamForm.createDummyPlayers(seriesForm.getPlayerInSquad());
      request.setAttribute("coreTeamForm", coreTeamForm);
      httpSession.setAttribute("coreTeamForm", coreTeamForm);
      httpSession.setAttribute(Constants.OPERATION, "Add");
    }
  if (session.getAttribute(Constants.SEARCHED_PLAYERS) == null)
  {
    session.setAttribute(Constants.SEARCHED_PLAYERS, ManagePlayerDBHelper.getAllPlayers());
  }

    return mapping.findForward(forward);

  }

}
