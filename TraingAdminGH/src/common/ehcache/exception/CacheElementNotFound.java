package common.ehcache.exception;

import common.exceptions.BusinessException;

public class CacheElementNotFound extends BusinessException
{

  /**
   * @param arg0
   */
  public CacheElementNotFound(Exception e, String message)
  {
    super(message, e);
    // TODO Auto-generated constructor stub
  }

  public CacheElementNotFound(String message)
  {
    super(message);
    // TODO Auto-generated constructor stub
  }

}
