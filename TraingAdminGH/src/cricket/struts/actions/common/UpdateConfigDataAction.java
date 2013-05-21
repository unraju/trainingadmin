package cricket.struts.actions.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.util.Constants;

import cricket.struts.actionforms.common.ConfigDataForm;

public class UpdateConfigDataAction extends Action
{

  @Override
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
  {
    ConfigDataForm configDataForm = (ConfigDataForm) form;
    String forward = "success";
    String operation = (String) request.getParameter(Constants.OPERATION);

    if (operation != null && operation.equalsIgnoreCase("Cancel"))
    {
      return mapping.findForward("cancel");
    }
    else if (operation != null && operation.equalsIgnoreCase("Save"))
    {
      
    }
    else if (operation != null && operation.equalsIgnoreCase("Update"))
    {

    }

    return mapping.findForward(forward);
  }

}
