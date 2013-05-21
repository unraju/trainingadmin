package cricket.struts.actions.common;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.filters.LoadStaticDataHelper;
import common.util.Constants;
import common.util.UserUtil;

import cricket.struts.actionforms.common.SCTNewsForm;
import cricket.struts.helpers.team.ManageTeamDBHelper;

public class ManageSCTNewsAction extends Action
{
  @Override
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
  {
    String operation = request.getParameter("operation");
    String forward = "success";
    HttpSession session = request.getSession();
    SCTNewsForm sctNewsForm = (SCTNewsForm) form;
    String newsId = request.getParameter("selectedId");
    ServletContext context = this.getServlet().getServletContext();
    try
    {
      if (operation != null && operation.equals(Constants.ADD_BTN_VALUE))
      {
        forward = "success";
        sctNewsForm = new SCTNewsForm();
        request.setAttribute(Constants.SCTNEWS_FORM, sctNewsForm);
        request.setAttribute(Constants.SCTNEWS_ADD_SCTION, Constants.SCTNEWS_ADD_SCTION);
      }
      else if (operation != null && operation.equals(Constants.UPDATE_BTN_VALUE))
      {
        if (newsId != null && !newsId.trim().equals(""))
        {
          forward = "success";
          sctNewsForm = ManageTeamDBHelper.findNewsById(Long.parseLong(newsId)).getActionForm();
          request.setAttribute(Constants.SCTNEWS_FORM, sctNewsForm);
          request.setAttribute(Constants.SCTNEWS_ADD_SCTION, Constants.SCTNEWS_ADD_SCTION);
        }
      }
      else if (operation != null && operation.equals(Constants.SAVE_BTN_VALUE))
      {
        
          forward = "forward";
          ManageTeamDBHelper.addSCTNews(sctNewsForm);
          //request.setAttribute(Constants.SCTNEWS_FORM, sctNewsForm);
          request.setAttribute(Constants.FORWARD_ACTION,Constants.UPDATE_SCT_NEWS_URL);
      }
      else if (operation != null && operation.equals(Constants.PUBLISH_NEWS))
      {
          forward = "success";
          new LoadStaticDataHelper().updateSeriesNEWSCache();
      }
      else if (operation != null && operation.equals(Constants.DELETE_BTN_VALUE))
      {
        ManageTeamDBHelper.deleteNews(Long.parseLong(newsId));
        forward = "forward";
        request.setAttribute(Constants.FORWARD_ACTION,Constants.UPDATE_SCT_NEWS_URL);
      }
      else if (operation != null && operation.equals(Constants.CANCEL_BTN_VALUE))
      {
        forward = "success";
      }
      else
      {
        List<SCTNewsForm> sctNewsForms = new LoadStaticDataHelper().getSCTNews(UserUtil.getSeries());
        session.setAttribute(Constants.SCT_NEWS, ManageTeamDBHelper.getAllSCTNews());
        //new LoadStaticDataHelper().updateCommonCache();
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      request.setAttribute(Constants.ERROR_MESSAGE, e.getLocalizedMessage());
      forward = "error";
      request.setAttribute("success", "");
    }
    return mapping.findForward(forward);
  }

}
