package br.com.benchmark.sampler.clear;

import br.com.benchmark.jmeter.SimpleSampleResult;
import br.com.benchmark.jmeter.TemplateSampler;
import br.com.benchmark.sampler.CassandraIDList;

/**
 * The Class CassandraClear.
 * @author astronauta
 */
public class CassandraClear extends TemplateSampler {

	/**
	 * Instantiates a new cassandra clear.
	 */
	public CassandraClear() {
		super("cassandra clear");
	}

	/* (non-Javadoc)
	 * @see br.com.benchmark.jmeter.TemplateSampler#execute()
	 */
	@Override
	protected SimpleSampleResult execute() {
		CassandraIDList.getInstance().clear();
		return new SimpleSampleResult("200", "CASSANDRA CLEARED", false);
	}
}