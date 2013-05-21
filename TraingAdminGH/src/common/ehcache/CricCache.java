package common.ehcache;

import java.io.Serializable;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import common.ehcache.exception.CacheElementNotFound;

public class CricCache implements ICache {

	protected Cache cache;
	private String _strCacheName;

	public CricCache() {
	}

	public CricCache(Cache a_oCache, String a_strCacheName) {
		cache = a_oCache;
		_strCacheName = a_strCacheName;

	}

	public Serializable get(Serializable key) throws CacheElementNotFound {

		Element element = cache.get(key);
		if (element == null)
		{
			return null;
		}

		return (Serializable) element.getObjectValue();
	}

	public boolean isCacheLoaded() {
		return cache.getKeysNoDuplicateCheck().size() == 0;
	}

	public void put(Serializable key, Serializable value) {
		Element element = new Element(key, value);
		cache.put(element);
	}

	public void remove(Serializable key) {
		cache.remove(key);
	}

	public void update(Serializable key, Serializable value) {
		Element element = new Element(key, value);
		cache.put(element);
	}

	public String getCacheName() {
		return _strCacheName;

	}

	public Cache getCache() {
		return cache;

	}
}
