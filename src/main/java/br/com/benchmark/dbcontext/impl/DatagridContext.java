package br.com.benchmark.dbcontext.impl;

import java.util.Properties;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.impl.ConfigurationProperties;

import br.com.benchmark.dbcontext.api.DBContext;

/**
 * The Class DatagridContext.
 * 
 * Created to manage access to JBoss Datagrid.
 * @author astronauta
 */
public class DatagridContext implements DBContext {
	
	/** The Constant instance. */
	private final static DatagridContext instance = new DatagridContext();
	
	/** The remote cache. */
	private RemoteCache<String, Object> remoteCache;

	/**
	 * Gets the single instance of DatagridContext.
	 *
	 * @return single instance of DatagridContext
	 */
	public static DatagridContext getInstance() { return instance; }
	
	/**
	 * Instantiates a new datagrid context.
	 */
	private DatagridContext() {
		String cache = "default";
		Properties properties = new Properties();
		properties.setProperty("testOnBorrow", "true");
		properties.setProperty("testWhileIdle", "true");
		properties.setProperty(ConfigurationProperties.SERVER_LIST, "localhost:11222");

		RemoteCacheManager remoteCacheManager = new RemoteCacheManager(properties);
		remoteCache = remoteCacheManager.getCache(cache);
	}
	
	/* (non-Javadoc)
	 * @see br.com.benchmark.dbcontext.api.DBContext#set(java.lang.String, java.lang.Object)
	 */
	@Override
	public <T> void set(String token, T value) {
		remoteCache.put(token, value);
	}

	/* (non-Javadoc)
	 * @see br.com.benchmark.dbcontext.api.DBContext#get(java.lang.String, java.lang.Class)
	 */
	@Override
	public <T> T get(String token, Class<? extends T> clazz) {
		return clazz.cast(remoteCache.get(token));
	}
	
	/* (non-Javadoc)
	 * @see br.com.benchmark.dbcontext.api.DBContext#clearDB()
	 */
	@Override
	public boolean clearDB() {
		remoteCache.clear();
		return false;
	}
}
