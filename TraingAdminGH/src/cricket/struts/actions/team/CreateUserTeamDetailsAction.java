package cricket.struts.actions.team;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.actionforms.user.UserForm;
import common.util.CheckSessionValidate;
import common.util.Constants;

import cricket.hibernate.bf.team.UserTeam;
import cricket.struts.actionforms.team.PlayerForm;
import cricket.struts.actionforms.team.UserTeamForm;
import cricket.struts.helpers.team.ManageTeamDBHelper;

public class CreateUserTeamDetailsAction extends org.apache.struts.action.Action
{

  @Override
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
  {

    CheckSessionValidate.isSessionValide(request, response);
    ManageTeamDBHelper manageTeamDBHelper = new ManageTeamDBHelper();
    UserTeam userTeam = null;
    HttpSession httpSession = request.getSession();
    String operation = request.getParameter("operation");
    String forward = "error";
    String captainId = (String) request.getParameter("captain");
    UserTeamForm userTeamForm = (UserTeamForm) form;
    UserTeamForm tempUserTeamForm = (UserTeamForm) httpSession.getAttribute(Constants.USER_TEAM);
    userTeamForm.setPlayers(tempUserTeamForm.getPlayers());
    //userTeamForm.setCoreTeams(tempUserTeamForm.getCoreTeams());
    if (operation.equalsIgnoreCase("Confirm"))
    {
      Set<String> playerList = new HashSet<String>();
      boolean allPalyerSelected = true;
      for (PlayerForm playerForm : userTeamForm.getPlayers())
      {
        String palyerId = (String) request.getParameter(playerForm.getId().toString() + "P1");

        if (palyerId != null && palyerId.startsWith("Please"))
        {
          allPalyerSelected = false;
        }
        else
        {
          playerForm.setTmpPlayerId(Long.parseLong(palyerId));
          if (captainId != null && captainId.equals(playerForm.getId().toString()))
          {
            playerForm.setCaptain(true);
          }
          else
          {
            playerForm.setCaptain(false);
          }
          if (!playerList.contains(palyerId))
          {
            playerForm.setChangedPlayerId(Long.parseLong(palyerId));
          }
          playerList.add(palyerId);
        }

      }
      List<String> errorMessages =  new ArrayList<String>();
      if (!allPalyerSelected)
      {
        //forward = "errorMessage";
        //request.setAttribute(Constants.ERROR_MESSAGE, "Please Select All Players.");
        //request.setAttribute(Constants.USER_TEAM, userTeamForm);
       // httpSession.setAttribute(Constants.USER_TEAM, userTeamForm);
        errorMessages.add( "Please Select All Players.");
        //return mapping.findForward(forward);
      }
      if (userTeamForm.getName() == null || (userTeamForm.getName() != null && userTeamForm.getName().equals("")))
      {
       // forward = "errorMessage";
       // request.setAttribute(Constants.ERROR_MESSAGE, "Please Select the Team Name.");
       // request.setAttribute(Constants.USER_TEAM, userTeamForm);
       // httpSession.setAttribute(Constants.USER_TEAM, userTeamForm);
        errorMessages.add( "Please Select the Team Name.");
        //return mapping.findForward(forward);
      }
      if (captainId == null)
      {
       // forward = "errorMessage";
       // request.setAttribute(Constants.ERROR_MESSAGE, "Please Select the Captain.");
       // request.setAttribute(Constants.USER_TEAM, userTeamForm);
       // httpSession.setAttribute(Constants.USER_TEAM, userTeamForm);
        errorMessages.add("Please Select the Captain.");
        //return mapping.findForward(forward);
      }

      if (playerList.size() < 11)
      {
        //forward = "errorMessage";
        //request.setAttribute(Constants.ERROR_MESSAGE, "Same Player Selected more than once.");
       
        errorMessages.add( "Same player is Selected more than once.");
      }
      if(errorMessages.size()>0)
      {
        forward = "errorMessage";
        request.setAttribute(Constants.USER_TEAM, userTeamForm);
        httpSession.setAttribute(Constants.USER_TEAM, userTeamForm);
        request.setAttribute(Constants.ERROR_MESSAGE, errorMessages);
        return mapping.findForward(forward);
      }
      if (playerList.size() != 11 || userTeamForm.getPlayers().size() != 11)
      {
        forward = "invalidSession";
        return mapping.findForward(forward);
      }
      UserForm user = (UserForm) httpSession.getAttribute(Constants.USER);
      userTeam= manageTeamDBHelper.saveUserTeamDetails(userTeamForm, user);
      forward = "success";
      request
      .setAttribute(Constants.FORWARD_ACTION,"manageUserTeam.do");
    }
    else if (operation.equalsIgnoreCase("Cancel"))
    {
      forward = "cancel";
    }
    UserForm user = (UserForm) httpSession.getAttribute(Constants.USER);
    UserTeamForm latestuserTeamForm =  null;
    
      latestuserTeamForm = manageTeamDBHelper.getUserTeamDetails(user.getId());
    
    if (latestuserTeamForm == null)
    {
      latestuserTeamForm = new UserTeamForm();
    }
    if (userTeamForm.getPlayers() == null || !(userTeamForm.getPlayers() != null && userTeamForm.getPlayers().size() > 0))
    {
      latestuserTeamForm.createDummyPlayers();
    }
    //latestuserTeamForm.setCoreTeams((ArrayList<CoreTeamForm>) manageTeamDBHelper.getAllCoreTeamDetails());
    //httpSession.setAttribute(Constants.USER_TEAM, latestuserTeamForm);
    //System.out.println("### CreateUserTeamAction- userTeamForm ###"+latestuserTeamForm);
    //request.setAttribute(Constants.USER_TEAM, latestuserTeamForm);
    request.setAttribute(Constants.OPERATION, Constants.UPDATE);
    return mapping.findForward(forward);

  }

}
