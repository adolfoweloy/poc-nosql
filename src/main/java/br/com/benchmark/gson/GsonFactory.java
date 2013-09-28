package br.com.benchmark.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * A factory for creating Gson objects.
 * @author carlos.ribeiro
 */
public class GsonFactory {
	
	/** The Constant instance. */
	private static final GsonFactory instance = new GsonFactory();
	
	/**
	 * Ge instance.
	 *
	 * @return the gson factory
	 */
	public static GsonFactory geInstance() {
		return instance;
	}

	/** The builder. */
	private GsonBuilder builder;
	
	/**
	 * Instantiates a new gson factory.
	 */
	private GsonFactory() {
		builder = new GsonBuilder().serializeNulls();
	}
	
	/**
	 * Registry.
	 *
	 * @param type the type
	 * @param adapter the adapter
	 * @return the gson factory
	 */
	public GsonFactory registry(Class<?> type, Object adapter){
		builder.registerTypeAdapter(type, adapter);
		return this;
	}
	
	/**
	 * Creates the.
	 *
	 * @return the gson
	 */
	public Gson create(){
		return builder.create();
	}
}