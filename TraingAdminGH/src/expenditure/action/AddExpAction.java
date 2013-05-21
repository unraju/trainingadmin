package expenditure.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.exceptions.BusinessException;
import common.struts.actionforms.user.UserForm;
import common.util.CheckSessionValidate;
import common.util.Constants;

import expenditure.actionform.AddExpActionForm;
import expenditure.helpers.HibernateDBHelper;

public class AddExpAction extends Action
{
  @Override
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
  {
    CheckSessionValidate.isSessionValide(request, response);
    AddExpActionForm expTo = (AddExpActionForm) form;
    String forward = "success";
    
    if (isTokenValid(request  ))
    {
      saveToken(request);
      
    } else
    {
    //errors.add("error.duplicaterequest", new ActionMessage("error.duplicaterequest"));
      request.setAttribute(Constants.ERROR_MESSAGE,"Failed -  Duplicate Form Data submitted");
      //resetToken(request);
      expTo = null;
      request.setAttribute("addExpForm", expTo);
      return (mapping.getInputForward());
    }
        
    try
    {

      HibernateDBHelper helper = new HibernateDBHelper();
      helper.addExpDetails(expTo, (UserForm) request.getSession(false).getAttribute(Constants.USER));
      expTo = null;
      request.setAttribute("addExpForm", expTo);
      request.setAttribute("success", "Detals added Sucessfully");

    }
    catch (BusinessException e)
    {
      e.printStackTrace();
      request.setAttribute(Constants.ERROR_MESSAGE, e.getLocalizedMessage());
      forward = "error";
      request.setAttribute("success", "");
    }

    return mapping.findForward(forward);

  }
  
}
