package br.com.benchmark.dbcontext.api;

/**
 * The Interface DBContext.
 * 
 * Provides API to set, get and clear all nosql database beign tested by application.
 * @author astronauta
 */
public interface DBContext {
	
	/** The Constant CONTENT. */
	static final String CONTENT =
			"ABCDEFGHIJKLMNOPQRSTUVXZWY0123456789-ABCDEFGHIJKLMNOPQRSTUVXZWY0123456789-ABCDEFGHIJKLMNOPQRSTUVXZWY0123456789-";
	
	/**
	 * Sets the.
	 *
	 * @param <T> the generic type
	 * @param token the token
	 * @param obj the obj
	 */
	<T> void set(String token, T obj);

	/**
	 * Gets the.
	 *
	 * @param <T> the generic type
	 * @param token the token
	 * @param clazz the clazz
	 * @return the t
	 */
	<T> T get(String token, Class<? extends T> clazz);

	/**
	 * Clear db.
	 *
	 * @return true, if successful
	 */
	boolean clearDB();

}