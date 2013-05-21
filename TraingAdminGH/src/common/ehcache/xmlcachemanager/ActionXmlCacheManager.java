package common.ehcache.xmlcachemanager;

import java.util.HashMap;
import java.util.Map;

import net.sf.ehcache.CacheManager;

import common.ehcache.CricCache;
import common.ehcache.ICache;
import common.ehcache.elements.CricRulesElement;
import common.ehcache.elements.UserRolesElement;
import common.ehcache.exception.CacheElementNotFound;
import common.filters.LoadStaticDataHelper;
import common.util.Constants;
import common.util.UserUtil;

public class ActionXmlCacheManager extends CricCache implements ICache {

	private static Map<String, CricCache> cacheList = new HashMap<String, CricCache>();

	private static CacheManager manager = null;

	private static Long seriesId = null;

	private static String cacheName = null;

	public static synchronized CricCache getInstance(String a_strModuleName) {
		CricCache cricCahe = null;
		if (!(a_strModuleName.equals(Constants.COMMON_CACHE) || a_strModuleName.equals(Constants.EXPENDITURE_CACHE) ))
		{
			seriesId = Long.parseLong(a_strModuleName);
		}
		cacheName = a_strModuleName;
		if (!cacheList.containsKey(a_strModuleName))
		{
			CricCache cricCache = new ActionXmlCacheManager(a_strModuleName);
			cacheList.put(a_strModuleName, cricCache);
			return cricCache;
		}
		else
		{
			cricCahe = (CricCache) cacheList.get(a_strModuleName);
			// Below code used to check any elements are null reload the cache.
			try
			{ // for Common Cache
				if (a_strModuleName.equals(Constants.COMMON_CACHE))
				{
					UserRolesElement userRolesElement = (UserRolesElement) cricCahe.get(UserRolesElement.USER_ROLES);
					if (userRolesElement == null || userRolesElement.getUserRoleForms() == null)
					{
						System.out.println("### Common Cache - UserRoleForms - Empty! Updating the Common Cache ###");
						new LoadStaticDataHelper().updateCommonCache(cricCahe);
					}
				}
				// For the series level cache
				else
				{
					CricRulesElement cricRulesElement = (CricRulesElement) cricCahe.get(CricRulesElement.CRIC_RULES);
					if (cricRulesElement == null || cricRulesElement.getCricRulesForms() == null)
					{
						System.out.println("### Series # "+UserUtil.getSeries()+" Cache - CRIC_RULES - Empty! Updating the Series Cache ###");
						new LoadStaticDataHelper().updateSeriesDetailsCache(cricCahe);
					}
				}
			}
			catch (CacheElementNotFound e)
			{
				e.printStackTrace();
			}
		}
		return cricCahe;
	}

	private ActionXmlCacheManager(String a_strModuleName) {
		try
		{
			manager = CacheManager.getInstance();

			if (!manager.cacheExists(a_strModuleName))
			{
				manager.addCache(a_strModuleName);
				cache = manager.getCache(a_strModuleName);
				if (cacheName.equalsIgnoreCase(Constants.COMMON_CACHE))
				{
					new LoadStaticDataHelper().loadCommonCacheDetails(this);
				}
				else if (cacheName.equalsIgnoreCase(Constants.EXPENDITURE_CACHE))
				{
					new LoadStaticDataHelper().loadExpenditureCacheDetails(this);
				}
				else if (seriesId != null)
				{
					new LoadStaticDataHelper().setSeriedSpecificCoreData(this, seriesId);
				}

			}
			else
			{
				cache = manager.getCache(a_strModuleName);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void reloadCache() {
		// manager.moveCache(cacheName);
		new LoadStaticDataHelper().setSeriedSpecificCoreData(this, seriesId);
	}
}
