package cricket.struts.actions.team;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.util.CheckSessionValidate;
import common.util.Constants;
import common.util.UserUtil;

import cricket.struts.actionforms.team.CoreTeamForm;
import cricket.struts.actionforms.team.TeamSheduleForm;
import cricket.struts.helpers.team.ManageTeamDBHelper;

public class UpdateTeamSheduleAction extends Action
{

  @Override
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
  {

    CheckSessionValidate.isSessionValide(request, response);
    ManageTeamDBHelper manageTeamDBHelper = new ManageTeamDBHelper();
    request.removeAttribute(Constants.VALIDATION_MESSAGE);
    HttpSession httpSession = request.getSession();
    String operation = request.getParameter("operation");
    String forward = "error";
    List<TeamSheduleForm> teamShedules = (ArrayList<TeamSheduleForm>) httpSession.getAttribute(Constants.TEAM_SHEDULE);
    List<CoreTeamForm> coreTeamForms = (ArrayList<CoreTeamForm>) httpSession.getAttribute(Constants.SEARCHED_CORE_TEAMS);
    if (operation.equalsIgnoreCase("Save"))
    {

      for (TeamSheduleForm teamSheduleForm : teamShedules)
      {
        String matchName = (String) request.getParameter(teamSheduleForm.getId().toString() + "T1");
        String firstTeamId = (String) request.getParameter(teamSheduleForm.getId().toString() + "T2");
        String secondTeamId = (String) request.getParameter(teamSheduleForm.getId().toString() + "T3");
        String date = (String) request.getParameter(teamSheduleForm.getId().toString() + "T4");
        String venue = (String) request.getParameter(teamSheduleForm.getId().toString() + "T5");
        String time = (String) request.getParameter(teamSheduleForm.getId().toString() + "T7");
        if (matchName == null || (matchName != null && matchName.trim().equals("")) || firstTeamId == null
            || (firstTeamId != null && firstTeamId.trim().equals("")) || secondTeamId == null
            || (secondTeamId != null && secondTeamId.trim().equals("")) || date == null || (date != null && date.trim().equals("")) || venue == null
            || (venue != null && venue.trim().equals("")) || (time != null && time.trim().equals("")))
        {
          forward = "errorMessage";
          request.setAttribute(Constants.VALIDATION_MESSAGE, "Please Enter all required data.");
          return mapping.findForward(forward);
        }
        if (teamSheduleForm.getId().longValue() > 1000)
        {
          teamSheduleForm.setId(null);
        }
        teamSheduleForm.setMatchName(matchName);
        teamSheduleForm.setFirstTeamId(Long.parseLong(firstTeamId));
        teamSheduleForm.setSecondTeamId(Long.parseLong(secondTeamId));
        teamSheduleForm.setDate(date);
        teamSheduleForm.setVenue(venue);
        teamSheduleForm.setTime(time);
      }
      manageTeamDBHelper.saveTeamSheduleDetails(teamShedules);
      httpSession.setAttribute(Constants.TEAM_SHEDULE,new ManageTeamDBHelper().getTeamSheduleDetails(UserUtil.getSeries()));
      forward = "success";
    }
    else if (operation.equalsIgnoreCase("Cancel"))
    {
      forward = "cancel";
    }
    return mapping.findForward(forward);

  }

}
