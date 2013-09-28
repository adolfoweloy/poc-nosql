package br.com.benchmark.sampler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Random ID cache.
 * 
 * This class allows for retrieving from cache and creating new entries within the cache.
 * This cache were created specific for Cassandra benchmark (I prefer to use it as a general cache regardless of db in test
 * as DBContext provides interface needed to clear cache).
 * 
 * @see DBContext
 * @author astronauta
 */
public class CassandraIDList {
	
	/** The Constant instance. */
	private final static CassandraIDList instance = new CassandraIDList();
	
	/** The random token list. */
	private List<Integer> randomTokenList = new ArrayList<Integer>();

	/**
	 * Gets the single instance of CassandraIDList.
	 *
	 * @return single instance of CassandraIDList
	 */
	public static CassandraIDList getInstance() {
		return instance;
	}
	
	/**
	 * Gets the random id.
	 *
	 * @return the random id
	 */
	public Integer getRandomId() {
		return randomTokenList.get((int) (Math.random() * randomTokenList.size()));
	}
	
	/**
	 * Adds the random id.
	 *
	 * @param nextInt the next int
	 * @return the integer
	 */
	public Integer addRandomId(int nextInt) {
		randomTokenList.add(nextInt);
		return nextInt;
	}

	/**
	 * Gets the random id size.
	 *
	 * @return the random id size
	 */
	public int getRandomIdSize() {
		return randomTokenList.size();
	}

	/**
	 * Gets the list.
	 *
	 * @return the list
	 */
	public List<Integer> getList() {
		return Collections.unmodifiableList(randomTokenList);
	}

	/**
	 * Creates the token.
	 *
	 * @return the int
	 */
	public int createToken() {
		return (int) (Math.random() * 1000000);
	}

	/**
	 * Clear.
	 */
	public void clear() {
		randomTokenList.clear();
	}
}