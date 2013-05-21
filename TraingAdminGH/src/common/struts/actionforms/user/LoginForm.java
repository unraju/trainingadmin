package common.struts.actionforms.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class LoginForm extends ActionForm
{

  private String loginName;

  private String pwd;

  private String name;

  private List<UserRoleForm> userRoles;

  public String getLoginName()
  {

    return loginName;
  }

  public void setLoginName(String loginName)
  {

    this.loginName = loginName;
  }

  public String getPwd()
  {

    return pwd;
  }

  public void setPwd(String pwd)
  {

    this.pwd = pwd;
  }

  public List<UserRoleForm> getUserRoles()
  {

    return userRoles;
  }

  public void setUserRoles(List<UserRoleForm> userRoles)
  {

    this.userRoles = userRoles;
  }

  public String getName()
  {

    return name;
  }

  public void setName(String name)
  {

    this.name = name;
  }

  @Override
  public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
  {

    // Requestor validations
    ActionErrors errors = new ActionErrors();
    if (loginName == null || loginName.trim().equals(""))
    {
      String[] runtimeValues = new String[2];
      runtimeValues[0] = "Login Name";
      errors.add("loginName", new ActionMessage("errors.required", runtimeValues));
    }

    if (pwd == null || pwd.trim().equals(""))
    {
      String[] runtimeValues = new String[2];
      runtimeValues[0] = "Password";
      errors.add("pwd", new ActionMessage("errors.required", runtimeValues));
    }
    return errors;
  }

}
