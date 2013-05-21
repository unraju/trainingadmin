package cricket.struts.actions.team;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.actionforms.user.UserForm;
import common.util.CheckSessionValidate;
import common.util.Constants;
import common.util.DateUtil;

import cricket.struts.actionforms.team.PlayerForm;
import cricket.struts.actionforms.team.UserTeamForm;
import cricket.struts.helpers.team.ManageTeamDBHelper;

public class PopulateUserTeamAction extends Action
{

  @Override
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
  {
    CheckSessionValidate.isSessionValide(request, response);
    String forward = "";
    HttpSession session = request.getSession();
    ManageTeamDBHelper manageTeamDBHelper = new ManageTeamDBHelper();
    UserForm user = (UserForm) session.getAttribute(Constants.USER);
    String operation = (String) request.getParameter("operation");
    String selectedPlayerId = request.getParameter("selectedId");
    String isNewTeam = "No";
    try
    {
      if (operation.equals(Constants.CHANGE_PLAYER))
      {
       /* if (false)//isAllowedTime())
        {
          request.setAttribute(Constants.ERROR_MESSAGE, "Change Player is allowed during 09:30 AM to 07:00 PM");
          forward = "validation";
          return mapping.findForward(forward);
        }*/
       
        UserTeamForm userTeamForm =   (UserTeamForm)session.getAttribute(Constants.USER_TEAM);
        UserTeamForm tempUserTeamForm = manageTeamDBHelper.getUserTeamDetails(user.getId());
        if (tempUserTeamForm.getScore().intValue() < -50)
        {
          request.setAttribute(Constants.ERROR_MESSAGE, "Your are not allowed to substitute player. As your Team score is -Ve.");
          forward = "validation";
          return mapping.findForward(forward);
        }
        if ((tempUserTeamForm.getSubstitutions() + tempUserTeamForm.getPaidSubstitutions()) <= tempUserTeamForm.getUsedSubstitutions())
        {
          request.setAttribute(Constants.ERROR_MESSAGE_2, "No more substitutions left.");
          userTeamForm.setBlockSubstitutions(true);
          // forward = "validation";
          //return mapping.findForward(forward);
        }
        userTeamForm.setPlayers(tempUserTeamForm.getPlayers());
        for (PlayerForm playerForm : userTeamForm.getPlayers())
        {
          if (selectedPlayerId.equals(playerForm.getId().toString()))
          {
            session.setAttribute(Constants.PLAYER_FORM, playerForm);
            request.setAttribute(Constants.PLAYER_FORM, playerForm);
            break;
          }
        }
        saveToken(request);
        forward = "changePlayer";
        session.setAttribute(Constants.USER_TEAM, userTeamForm);
      }
      
      // for updating the all user players.
      else if (operation.equalsIgnoreCase("Click here to change Team"))
      {
        UserTeamForm userTeamForm = manageTeamDBHelper.getUserTeamDetails(user.getId());
        if (userTeamForm == null)
        {
          userTeamForm = new UserTeamForm();
        }
        if (userTeamForm.getPlayers() == null || !(userTeamForm.getPlayers() != null && userTeamForm.getPlayers().size() > 0))
        {
          userTeamForm.createDummyPlayers();
          isNewTeam = "Yes";
        }
        //userTeamForm.setCoreTeams((ArrayList<CoreTeamForm>) manageTeamDBHelper.getAllCoreTeamDetails());
        session.setAttribute("userTeamForm", userTeamForm);
        request.setAttribute("userTeamForm", userTeamForm);
        forward = "update";
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      forward = "error";
      request.setAttribute("error", e.getLocalizedMessage());
    }
    request.setAttribute(Constants.CANCEL_BTN_NEED,isNewTeam);
    return mapping.findForward(forward);
  }

  private boolean isAllowedTime() throws Exception
  {
    Date currentDate = DateUtil.getCurrentDateAsTimestamp();

    String currentDateInString = DateUtil.getDateFromStringFormat_dd_MMM_yyyy();
    String ld = currentDateInString + " 09:30:00";
    String ud = currentDateInString + " 19:00:00";
    String cutOffTFDateString = "19-Feb-2011 09:30:00";
    ud= cutOffTFDateString;
    Date startTime = DateUtil.getDateFromStringFormatDATE_TIME(ld);
    Date endTime = DateUtil.getDateFromStringFormatDATE_TIME(ud);
    Date cutOffTFDate = DateUtil.getDateFromStringFormatDATE_TIME(cutOffTFDateString);

    boolean changePlayer = currentDate.after(startTime) && currentDate.before(endTime);
    boolean update = currentDate.before(cutOffTFDate);

    //System.out.println("### Changed Plater###" + changePlayer);
    //System.out.println("### Update###" + changePlayer);
  
    return (!changePlayer);
  }

}
