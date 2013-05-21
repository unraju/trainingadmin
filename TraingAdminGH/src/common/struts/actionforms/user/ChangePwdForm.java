package common.struts.actionforms.user;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class ChangePwdForm extends ActionForm
{

  private String oldPwd;

  private String newPwd;

  private String cfPwd;

  public String getOldPwd()
  {
    return oldPwd;
  }

  public void setOldPwd(String oldPwd)
  {
    this.oldPwd = oldPwd;
  }

  public String getNewPwd()
  {
    return newPwd;
  }

  public void setNewPwd(String newPwd)
  {
    this.newPwd = newPwd;
  }

  public String getCfPwd()
  {
    return cfPwd;
  }

  public void setCfPwd(String cfPwd)
  {
    this.cfPwd = cfPwd;
  }

  @Override
  public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
  {

    ActionErrors errors = new ActionErrors();
    String operation = (String) request.getParameter("operation");

    if (operation != null && operation.equalsIgnoreCase("Confirm"))
    {
    String[] runtimeValues = new String[3];
    if (oldPwd == null || oldPwd.trim().equals(""))
    {
      runtimeValues[0] = "Old Password";
      errors.add("oldPwd", new ActionMessage("errors.required", runtimeValues[0]));
      this.oldPwd = "";

    }
    if (newPwd == null || newPwd.trim().equals(""))
    {
      runtimeValues[1] = "New Password";
      errors.add("newPwd", new ActionMessage("errors.required", runtimeValues[1]));
      this.newPwd = "";

    }
    if (cfPwd == null || cfPwd.trim().equals(""))
    {
      runtimeValues[2] = "Confirm Password";
      errors.add("cfPwd", new ActionMessage("errors.required", runtimeValues[2]));
      this.cfPwd = "";
    }
    }
    return errors;
  }

}
