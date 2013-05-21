package common.util;

import java.util.Comparator;

public class UserRoleFormComparator<UserRoleForm> implements Comparator<UserRoleForm>
{

  public int compare(Object arg0, Object arg1)
  {
    // parameter are of type Object, so we have to downcast it to Employee
    // objects
    UserRoleForm userRoleForm1 = (UserRoleForm)arg0;
    
    UserRoleForm userRoleForm2 = (UserRoleForm)arg1;

    int priority1 = 0;//Integer.parseInt(userRoleForm1.getClass()tClass()p);

    int priority2 = 0;//Integer.parseInt((UserRoleForm) arg1.getPriority());

    if (priority1 > priority2)

      return 1;

    else if (priority1 < priority2)

      return -1;

    else

      return 0;
  }

}
