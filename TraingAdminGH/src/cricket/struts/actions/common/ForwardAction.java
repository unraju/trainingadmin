package cricket.struts.actions.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.util.Constants;

public class ForwardAction extends Action
{

  @Override
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
  {
  HttpSession session = request.getSession();
  session.removeAttribute("serchedresults");
  session.removeAttribute("monthlyserchedresults");
  session.removeAttribute(Constants.SEARCHED_PLAYERS);
  String forward = "success";


  return mapping.findForward(forward);
  }

}
