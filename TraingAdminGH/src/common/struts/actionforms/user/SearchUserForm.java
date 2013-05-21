package common.struts.actionforms.user;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class SearchUserForm extends ActionForm
{

  private String loginName;

  private String userName;

  private String role;
  
  private boolean active = Boolean.TRUE;
  

  public String getLoginName()
  {

    return loginName;
  }

  public void setLoginName(String loginName)
  {

    this.loginName = loginName;
  }

  public String getRole()
  {

    return role;
  }

  public void setRole(String role)
  {

    this.role = role;
  }

  public String getUserName()
  {

    return userName;
  }

  public void setUserName(String userName)
  {

    this.userName = userName;
  }

  public boolean isActive()
  {
    return active;
  }

  public void setActive(boolean active)
  {
    this.active = active;
  }

  @Override
  public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
  {

    // Requester validations
    ActionErrors errors = new ActionErrors();
    if (loginName == null || loginName.trim().equals(""))
    {
      String[] runtimeValues = new String[2];
      runtimeValues[0] = "Login Name";
      errors.add("loginName", new ActionMessage("errors.required", runtimeValues));
    }

    if (userName == null || userName.trim().equals(""))
    {
      String[] runtimeValues = new String[2];
      runtimeValues[0] = "User Name";
      errors.add("userName", new ActionMessage("errors.required", runtimeValues));
    }
    return errors;
  }

}
