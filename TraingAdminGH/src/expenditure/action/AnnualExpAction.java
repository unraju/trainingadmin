package expenditure.action;

import java.util.List;

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

import expenditure.actionform.AddExpActionForm;
import expenditure.actionform.MonthlyExpActionForm;
import expenditure.helpers.HibernateDBHelper;

public class AnnualExpAction extends Action
{

  @Override
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
  {
    CheckSessionValidate.isSessionValide(request, response);
    MonthlyExpActionForm expTo = (MonthlyExpActionForm) form;
    expTo.setCashFlowType(request.getParameter("cashFlowType"));
    HttpSession httpSession = request.getSession();
    String forward = "success";

    try
    {
      List<AddExpActionForm> list = HibernateDBHelper.viewYearlyExpDetailsUsingHibernate(expTo, (UserForm) request.getSession().getAttribute(
          Constants.USER));
      httpSession.setAttribute("monthlyserchedresults", list);

    }
    catch (Exception e)
    {
      e.printStackTrace();
      request.setAttribute("error", e.getLocalizedMessage());
      forward = "error";
    }

    return mapping.findForward(forward);
  }
}
