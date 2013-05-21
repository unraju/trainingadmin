package common.ehcache;

import java.io.Serializable;

import net.sf.ehcache.Cache;

import common.ehcache.exception.CacheElementNotFound;

public interface ICache {
	
	void put(Serializable key, Serializable value);

	Serializable get(Serializable key) throws CacheElementNotFound;

	void update(Serializable key, Serializable value);

	void remove(Serializable key);

	boolean isCacheLoaded();

	String getCacheName();

	Cache getCache();
}
