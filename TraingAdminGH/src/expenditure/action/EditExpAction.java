package expenditure.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.util.CheckSessionValidate;
import common.util.Constants;

import expenditure.actionform.AddExpActionForm;
import expenditure.helpers.HibernateDBHelper;

public class EditExpAction extends org.apache.struts.action.Action
{

  @Override
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
  {
    CheckSessionValidate.isSessionValide(request, response);
    String operation = request.getParameter("operation");
    AddExpActionForm addExpActionForm = (AddExpActionForm) form;
    int id = addExpActionForm.getId();// Integer.parseInt(request.getParameter("id"));
    String forward = "error";
    if (operation.equalsIgnoreCase("edit") || operation.equalsIgnoreCase("delete"))
    {
      if (id == 0)
      {
        request.setAttribute(Constants.ERROR_MESSAGE, "select record for doing edit/delete.");
        forward = "validation";
        return mapping.findForward(forward);
      }
    }

    HttpSession httpSession = request.getSession();
    List<AddExpActionForm> list = (ArrayList<AddExpActionForm>) httpSession.getAttribute("serchedresults");
    for (AddExpActionForm actionForm : list)
    {
      if (id == actionForm.getId())
      {
        form = actionForm;
        break;
      }
    }

    if (operation.equalsIgnoreCase("edit"))
    {
      forward = "edit";
      request.setAttribute("addExpForm", form);
    }
    else if (operation.equalsIgnoreCase("delete"))
    {
      forward = "delete";
      HibernateDBHelper.deleteExpensive(form);
      list.remove(form);
      httpSession.setAttribute("serchedresults", list);
    }

    return mapping.findForward(forward);

  }

}
