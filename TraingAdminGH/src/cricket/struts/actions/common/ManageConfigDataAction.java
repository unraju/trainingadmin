package cricket.struts.actions.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.util.CheckSessionValidate;
import common.util.Constants;

import cricket.struts.actionforms.common.ConfigDataForm;
import cricket.struts.helpers.common.ManageConfigDataHelper;

public class ManageConfigDataAction extends Action
{

  @Override
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
  {

    CheckSessionValidate.isSessionValide(request, response);
    ManageConfigDataHelper helper = new ManageConfigDataHelper();
     String forward = "success";

    ConfigDataForm configDataForm = (ConfigDataForm) form;
    String operation = (String) request.getParameter(Constants.OPERATION);

    if (operation != null && operation.equalsIgnoreCase("Cancel"))
    {
      forward = "cancel";
      return mapping.findForward(forward);
    }
    else if (operation != null && operation.equalsIgnoreCase(Constants.SAVE))
    {
      helper.saveConfigData(configDataForm);
      request.setAttribute(Constants.DISABLED, Boolean.TRUE);
      request.setAttribute(Constants.SUBMIT_LABEL, Constants.UPDATE);
    }
    else if (operation != null && operation.equalsIgnoreCase(Constants.UPDATE))
    {
      request.setAttribute(Constants.DISABLED, Boolean.FALSE);
      request.setAttribute(Constants.SUBMIT_LABEL, Constants.SAVE);
    }
    request.setAttribute(Constants.CONFIG_DATA,  helper.getConfigData());
    if(request.getAttribute(Constants.SUBMIT_LABEL) == null)
    {
      request.setAttribute(Constants.SUBMIT_LABEL, Constants.UPDATE);
    }
    if(request.getAttribute(Constants.DISABLED) == null)
    {
      request.setAttribute(Constants.DISABLED,  Boolean.TRUE);
    }
    return mapping.findForward(forward);
  }

}
