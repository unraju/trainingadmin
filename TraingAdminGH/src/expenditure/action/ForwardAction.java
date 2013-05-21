package expenditure.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.hibernate.bf.user.User;
import common.util.Constants;

import expenditure.actionform.CashFlowTypeTO;
import expenditure.actionform.ItemTypeTO;
import expenditure.businessfunction.ItemTypeHome;

public class ForwardAction extends Action
{

  @Override
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
  {

    HttpSession session = request.getSession(false);
    session.removeAttribute("serchedresults");
    session.removeAttribute("monthlyserchedresults");

    User user = null;// (User)session.getAttribute(cricket.common.util.Constants.USER);
    if (session.getAttribute(Constants.ITEM_TYPES) == null)
    {
      List<ItemTypeTO> itemTypes = ItemTypeHome.getExpItemTypes();
      session.setAttribute(Constants.ITEM_TYPES, itemTypes);
    }
    if (session.getAttribute(Constants.CASH_FLOW_TYPES) == null)
    {
      List<CashFlowTypeTO> cashFlowTypes = ItemTypeHome.getCashFlowTypes();
      session.setAttribute(Constants.CASH_FLOW_TYPES, cashFlowTypes);
    }
    /*
     * String menu = (String) request.getParameter("menu"); String submenu =
     * (String) request.getParameter("submenu");
     * 
     * if (menu != null) { session.setAttribute("menu", menu); } if (submenu !=
     * null) { session.setAttribute("submenu", submenu); }
     */ 
    String forward = "success";
    saveToken(request);
    return mapping.findForward(forward);
  }

}
