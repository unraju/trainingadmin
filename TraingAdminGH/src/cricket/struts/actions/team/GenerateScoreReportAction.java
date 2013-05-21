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

import common.ehcache.xmlcachemanager.ActionXmlCacheManager;
import common.filters.LoadStaticDataHelper;
import common.util.Constants;
import common.util.UserUtil;

import cricket.hibernate.bf.team.ScoreReport;
import cricket.struts.actionforms.team.ScoreReportForm;
import cricket.struts.helpers.common.GenerateMailHelper;

public class GenerateScoreReportAction extends Action
{

  @Override
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
  {
    String forward = "success";
    String operation = (String) request.getParameter(Constants.OPERATION);
    HttpSession session = (HttpSession) request.getSession();
    List<ScoreReport> scoreReopts = null;
    //ServletContext ctx = getServlet().getServletContext();

    if (operation != null && operation.equalsIgnoreCase("Generate Report"))
		{
			if (UserUtil.getSeries().intValue() == 3)
			{
				new GenerateScoreReporHelper().generateUserTeamsReport();
				new GenerateScoreReporHelper().generateCoreTeamsReport();
			}
			request.setAttribute(Constants.FORWARD_ACTION, Constants.GENERATE_SCORE_REPORT_URL);
			return mapping.findForward("forward");

		}
    else if (operation != null && operation.equalsIgnoreCase(Constants.SEND_MAIL_BTN))
		{

			new GenerateMailHelper().mailScoreReport();
			// new GenerateMailHelper().sendSeriesLaunchMail();
			return mapping.findForward(forward);
		}
    else if (operation != null && operation.equalsIgnoreCase(Constants.PUBLISH_REPORTS))
    {
      // Map<String, Object> map = new
      // LoadStaticDataHelper().publishSeriesDetail(UserUtil.getSeries());
      // ctx.removeAttribute(UserUtil.getSeries().toString());
      // ctx.setAttribute(UserUtil.getSeries().toString(), map);
       new LoadStaticDataHelper().updateSeriesDetailsCache(ActionXmlCacheManager.getInstance(Constants.COMMON_CACHE));
       new LoadStaticDataHelper().updateCommonCache(ActionXmlCacheManager.getInstance(UserUtil.getSeries().toString()));
      return mapping.findForward(forward);
    }

    scoreReopts = new GenerateScoreReporHelper().getGeneratedReportHistory();
    if (scoreReopts == null)
    {
      scoreReopts = new ArrayList<ScoreReport>();
    }
    List<ScoreReportForm> scoreReportForms = new ArrayList<ScoreReportForm>();
    int size = 0;
    for (ScoreReport scoreReport : scoreReopts)
    {
      size++;
      scoreReportForms.add(scoreReport.getActionForm());
      if (size > 9)
      {
        break;
      }
    }
    session.setAttribute(Constants.SCORE_REPORTS, scoreReportForms);

    return mapping.findForward(forward);
  }

}
