package common.util;

import common.struts.actionforms.user.UserForm;

public class UserUtil
{

  private static final ThreadLocal threadSession = new ThreadLocal();

  private static final ThreadLocal threadSeries = new ThreadLocal();


  public static UserForm getCurrentUser()
  {
    UserForm userForm = (UserForm) threadSession.get();
    return userForm;
  }

  public static void setCurrentUser(UserForm form)
  {
    threadSession.set(form);
  }

  public static Long getSeries()
  {
    Long seriesId = (Long) threadSeries.get();
    return seriesId;
  }

  public static void setSelectedSeries(Long seriesId)
  {
    threadSeries.set(seriesId);
  }

  public static boolean isLogNeeded()
  {
    UserForm userForm = getCurrentUser();
    if (userForm != null && (userForm.getId().intValue() < 10 || userForm.getId().intValue() == 102))
    {
      return true;
    }
    else
    {
      return false;
    }
  }
}
