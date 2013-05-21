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
import common.util.UserUtil;

import cricket.hibernate.bf.common.Series;
import cricket.struts.actionforms.team.UserTeamForm;
import cricket.struts.helpers.team.ManageTeamDBHelper;

public class ManageUserTeamAction extends Action
{

  @Override
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
  {
    CheckSessionValidate.isSessionValide(request, response);
    HttpSession session = request.getSession();
    session.removeAttribute(Constants.OPERATION);
    ManageTeamDBHelper manageTeamDBHelper = new ManageTeamDBHelper();
    
    String forward = "success";
    try
    {
      UserForm user = (UserForm) session.getAttribute(Constants.USER);
      if (user == null)
      {
        forward = "invalidSession";
        return mapping.findForward(forward);
      }
          UserTeamForm userTeamForm = manageTeamDBHelper.getUserTeamDetails(user.getId());
      if (userTeamForm == null || userTeamForm.getPlayers() == null || !(userTeamForm.getPlayers() != null && userTeamForm.getPlayers().size() > 0))
      {
        if (userTeamForm == null)
        {
          userTeamForm = new UserTeamForm();
        }
        userTeamForm.createDummyPlayers();
        forward = "createTeam";
      }
      //userTeamForm.setCoreTeams((ArrayList<CoreTeamForm>) manageTeamDBHelper.getAllCoreTeamDetails());
      session.setAttribute("userTeamForm", userTeamForm);
      //request.setAttribute("userTeamForm", userTeamForm);
      //System.out.println("### ManageUserTeamAction- userTeamForm ###"+userTeamForm);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      forward = "error";
      request.setAttribute("error", e.getLocalizedMessage());
    }
  /*  Date currentDate = DateUtil.getCurrentDateAsTimestamp();

    String currentDateInString = DateUtil.getDateFromStringFormatteddd_MMM_yyyy();
    String ld = currentDateInString + " 09:30:00";
    String ud = currentDateInString + " 23:00:00";
    String cutOffTFDateString = "30-Apr-2010 19:00:00";

    Date startTime = DateUtil.getDateFromStringFormatDATE_TIME(ld);
    Date endTime = DateUtil.getDateFromStringFormatDATE_TIME(ud);
    Date cutOffTFDate = DateUtil.getDateFromStringFormatDATE_TIME(cutOffTFDateString);

    boolean changePlayer = currentDate.after(startTime) && currentDate.before(endTime);
    boolean update = currentDate.before(cutOffTFDate);
    
    System.out.println("### Changed Plater###" + changePlayer);
    System.out.println("### Update###" + changePlayer);*/
    Series series = manageTeamDBHelper.getCurrentSeriesById(UserUtil.getSeries());
    if(series != null)
  {
    Date now = DateUtil.getCurrentDateAsTimestampinGMT0530();
    //System.out.println("### Start Date ###"+DateUtil.getStringFromDate(series.getStartDate(),Constants.DATE_TEME_AM__PM_SHORT));
    //System.out.println("### End Date ###"+DateUtil.getStringFromDate(series.getEndDate(),Constants.DATE_TEME_AM__PM_SHORT));
    // System.out.println("### Now ###"+DateUtil.getStringFromDate(now,Constants.DATE_TEME_AM__PM_SHORT));
    if (now.before(series.getStartDate()))
    {
      session.setAttribute(Constants.OPERATION, Constants.UPDATE);
    }
    else if(now.after(series.getStartDate()) && now.before(series.getEndDate()))
    {
      session.setAttribute(Constants.OPERATION, Constants.CHANGE_PLAYER);
    }
  }
   /* if (false)
    {
      session.setAttribute(Constants.OPERATION, Constants.CHANGE_PLAYER);
    }
   if (false)
    {
      session.setAttribute(Constants.OPERATION, Constants.UPDATE);
    }*/
   
    return mapping.findForward(forward);

  }
}
