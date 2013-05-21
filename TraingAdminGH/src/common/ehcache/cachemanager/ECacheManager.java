package common.ehcache.cachemanager;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import net.sf.ehcache.CacheManager;

import common.ehcache.CricCache;
import common.ehcache.ICache;

public final class ECacheManager
{

  /**
   * Map of cache names i.e. SystemCache, UserDataCache etc as key and entries
   * as value.
   */
  private static HashMap cacheList = new HashMap();

  /**
   * ehCache manager responsible for data life cycle management.
   */
  private static CacheManager manager = CacheManager.create();

  /**
   * Singleton instance of cache manager.
   */
  private static ECacheManager _oECacheManager = null;

  private static boolean CACHE_DEBUG_MODE = false;

  /**
   * This method returns the singleton instance of the ecachemanager.
   * 
   * @return ECacheManager instance.
   */
  public static ECacheManager getInstance()
  {

    if (_oECacheManager == null)
    {
      _oECacheManager = new ECacheManager();
    }
    return _oECacheManager;
  }

  /**
   * This method returns the instance of the cache.
   * 
   * @param a_strCacheName
   *          The name of the cache.
   * @return Cache instance.
   */
  public ICache getCricCache(String a_strCacheName) throws Exception
  {

    CricCache local = null;
    ICache eCache = null;

    if (manager.cacheExists(a_strCacheName))
    {
     // local = manager.getCache(a_strCacheName);
    }
    else
    {
      manager.addCache(a_strCacheName);
     // local = manager.getCache(a_strCacheName);

    }

    System.out.println("INSIDE ECHE MANAGER -->>>>>> Cache Local  " + local.toString());
    System.out.println("INSIDE ECHE MANAGER -->>>>>> a_strCacheName       " + a_strCacheName);
    //eCache = new CricCache(local, a_strCacheName);

    System.out.println("INSIDE ECHE MANAGER -->>>>>>  eCache" + eCache);
    // a_strCacheName is same as cache query name
    loadMasterInCache(eCache);
    cacheList.put(a_strCacheName, eCache);
    return eCache;
  }

  /**
   * This method refresh the memory cache data from the database.
   * 
   * @param a_strCacheName
   *          The name of the cache.
   */
  public void reloadCache(String a_strCacheName) throws Exception
  {

    loadMasterInCache(getCricCache(a_strCacheName));
  }

  /**
   * TThis method loads the data from the database and updates in memory cache.
   * 
   * @param a_oICache
   *          The cache instance.
   */
  private void loadMasterInCache(ICache a_oICache) throws Exception
  {

    String strPrefix = a_oICache.getCacheName();

    Connection oConnection = null;
    Statement oStatement = null;

  }

  private void printCacheData(String a_strCacheName, String a_strCacheKey, ArrayList a_oList)
  {

    ArrayList listValue = (ArrayList) a_oList.get(0);
    ArrayList listText = (ArrayList) a_oList.get(1);

    int size = listValue.size();

    StringBuffer recordSb = new StringBuffer(1024 * 4);
    for (int i = 0; i < size; i++)
    {

      recordSb.append("\n Cache Name->" + a_strCacheName + "->Cache Key->" + a_strCacheKey + "->Row =" + i);
      recordSb.append("->Text=" + listText.get(i));
      recordSb.append("->Value=" + listValue.get(i));

    }

  }
}
