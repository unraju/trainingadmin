package common.ehcache.xmlcachemanager.cache;

import java.io.Serializable;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import common.ehcache.exception.CacheElementNotFound;


public class ElixirCache implements ICache {

	
	protected Cache cache;

	public Serializable get(Serializable key) throws CacheElementNotFound{
		
		Element element = cache.get(key);
//Start:05Nov2008:Mukesh/Chintan:Changed for Cache Not found.		
		if(element == null){
			
			return null; 
		}
//End:05Nov2008:Mukesh/Chintan:Changed for Cache Not found.		
				        
		return (Serializable) element.getObjectValue();
	}

	public boolean isCacheLoaded() {
		return cache.getKeysNoDuplicateCheck().size() == 0;
	}

	public void put(Serializable key, Serializable value) {
		Element element = new Element(key,value);
		cache.put(element);
	}

	public void remove(Serializable key) {
		cache.remove(key);
	}

	public void update(Serializable key, Serializable value) {
		Element element = new Element(key,value);
		cache.put(element);
	}
}
