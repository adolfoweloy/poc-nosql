package br.com.benchmark.dbcontext.impl;

import br.com.benchmark.dbcontext.api.DBContext;
import br.com.benchmark.dbcontext.ext.CassandraManager;
import br.com.benchmark.gson.GsonFactory;

import com.google.gson.Gson;

/**
 * The Class CassandraContext.
 */
public class CassandraContext implements DBContext {
	
	/** The Constant instance. */
	private final static CassandraContext instance = new CassandraContext();
	
	/** The manager. */
	private final CassandraManager manager = new CassandraManager();
	
	/**
	 * Instantiates a new cassandra context.
	 */
	private CassandraContext() {
		manager.init();
	}
	
	/**
	 * Gets the single instance of CassandraContext.
	 *
	 * @return single instance of CassandraContext
	 */
	public static CassandraContext getInstance() {
		return instance;
	}
	
	/* (non-Javadoc)
	 * @see br.com.benchmark.dbcontext.api.DBContext#set(java.lang.String, java.lang.Object)
	 */
	@Override
	public <T> void set(String token, T obj) {
		Gson gson = GsonFactory.geInstance().create();
		String value = gson.toJson(obj, obj.getClass());
		manager.update(token, value);
	}
	
	/* (non-Javadoc)
	 * @see br.com.benchmark.dbcontext.api.DBContext#get(java.lang.String, java.lang.Class)
	 */
	@Override
	public <T> T get(String token, Class<? extends T> clazz) {
		String value = manager.read(token);
		Gson gson = GsonFactory.geInstance().create();
		return gson.fromJson(value, clazz);
	}
	
	/* (non-Javadoc)
	 * @see br.com.benchmark.dbcontext.api.DBContext#clearDB()
	 */
	@Override
	public boolean clearDB() {
		return manager.dropColumnFamily();
	}
}
