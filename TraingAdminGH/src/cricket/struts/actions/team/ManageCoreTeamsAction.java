package cricket.struts.actions.team;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.util.Constants;
import common.util.UserUtil;

import cricket.struts.helpers.team.ManageTeamDBHelper;

public class ManageCoreTeamsAction extends Action
{

  @Override
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
  {
  // CheckSessionValidate.isSessionValide(request, response);
  //String seriesId = (String)request.getParameter(Constants.SERIES_ID);
  String forward = "error";
  try
  {
    HttpSession session = request.getSession();

    session.setAttribute(Constants.SEARCHED_CORE_TEAMS, new ManageTeamDBHelper().getAllCoreTeamDetails(UserUtil.getSeries()));

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
