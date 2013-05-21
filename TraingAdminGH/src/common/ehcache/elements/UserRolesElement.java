package common.ehcache.elements;

import java.io.Serializable;
import java.util.List;

import common.struts.actionforms.user.UserRoleForm;

public class UserRolesElement implements Serializable
{
  public static final String USER_ROLES = "USER_ROLES";

  public List<UserRoleForm> userRoleForms;

  public UserRolesElement(List<UserRoleForm> roleForms)
  {
    super();
    this.userRoleForms = roleForms;
  }

  public List<UserRoleForm> getUserRoleForms()
  {
    return userRoleForms;
  }

}
