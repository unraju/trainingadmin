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

import expenditure.actionform.AddExpActionForm;
import expenditure.helpers.HibernateDBHelper;

public class UpdateExpAction extends org.apache.struts.action.Action
{

  @Override
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
  {
    CheckSessionValidate.isSessionValide(request, response);
    String operation = request.getParameter("operation");
    AddExpActionForm addExpActionForm = (AddExpActionForm) form;

    // int id = Integer.parseInt(request.getParameter("id"));

    HttpSession httpSession = request.getSession();
    List<AddExpActionForm> list = (ArrayList<AddExpActionForm>) httpSession.getAttribute("serchedresults");

    String forward = "error";
    if (operation.equalsIgnoreCase("update"))
    {
      HibernateDBHelper.updateExpDetails(addExpActionForm);
      for (AddExpActionForm actionForm : list)
      {
        if (addExpActionForm.getId() == actionForm.getId())
        {
          actionForm.copy(addExpActionForm);
          addExpActionForm = actionForm;
          break;
        }
      }
      httpSession.setAttribute("serchedresults", list);
      forward = "success";

    }
    else if (operation.equalsIgnoreCase("delete"))
    {
      HibernateDBHelper.deleteExpensive(addExpActionForm);
      for (AddExpActionForm actionForm : list)
      {
        if (addExpActionForm.getId() == actionForm.getId())
        {
          addExpActionForm = actionForm;
          break;
        }
      }
      list.remove(addExpActionForm);
      httpSession.setAttribute("serchedresults", list);
      forward = "success";
    }
    else if (operation.equalsIgnoreCase("Cancel"))
    {
      forward = "success";
    }

    return mapping.findForward(forward);

  }

}
