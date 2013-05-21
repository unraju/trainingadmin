package common.ehcache.xmlcachemanager.cache;


import java.io.Serializable;

import common.ehcache.exception.CacheElementNotFound;

/**
 *@author prashantp
 *@version $Revision: 1.1 $
 */
public interface ICache {
	void put(Serializable key, Serializable value);
	Serializable get(Serializable key) throws CacheElementNotFound;
	void update(Serializable key, Serializable value);
	void remove(Serializable key);
	boolean isCacheLoaded();
}
