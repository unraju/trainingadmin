package cricket.struts.actions.team;

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
import common.util.UserUtil;

import cricket.struts.actionforms.common.SeriesForm;
import cricket.struts.actionforms.team.TeamSheduleForm;
import cricket.struts.helpers.team.ManageTeamDBHelper;

public class ManageMatchScoresAction extends Action
{

  @Override
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
  {
    CheckSessionValidate.isSessionValide(request, response);
    String forward = "error";
    ManageTeamDBHelper manageTeamDBHelper = new ManageTeamDBHelper();
    ServletContext ctx = getServlet().getServletContext();
    SeriesForm seriesForm = new RetriveContextData().getCurrentSeriesForm();
    try
    {
      HttpSession session = request.getSession();

      List<TeamSheduleForm> lteamSheduleForms = manageTeamDBHelper.getTeamSheduleDetails(UserUtil.getSeries());
      if (!(lteamSheduleForms.size() > 10))
      {
        TeamSheduleForm.getDummyTeamSheduleForms(lteamSheduleForms, seriesForm.getMatches());
      }
      session.setAttribute(Constants.TEAM_SHEDULE, lteamSheduleForms);

      session.setAttribute(Constants.SEARCHED_CORE_TEAMS, manageTeamDBHelper.getAllCoreTeamDetails(UserUtil.getSeries()));
      forward = "success";
    }
    catch (Exception e)
    {
      e.printStackTrace();
      request.setAttribute("error", e.getLocalizedMessage());
    }
    return mapping.findForward(forward);
  }

}
