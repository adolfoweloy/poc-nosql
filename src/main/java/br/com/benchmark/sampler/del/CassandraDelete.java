package br.com.benchmark.sampler.del;

import br.com.benchmark.dbcontext.impl.CassandraContext;
import br.com.benchmark.jmeter.SimpleSampleResult;
import br.com.benchmark.jmeter.TemplateSampler;

/**
 * The Class CassandraDelete.
 * @author astronauta
 */
public class CassandraDelete extends TemplateSampler {

	/**
	 * Instantiates a new cassandra delete.
	 */
	public CassandraDelete() {
		super("cassandra delete");
	}

	/* (non-Javadoc)
	 * @see br.com.benchmark.jmeter.TemplateSampler#execute()
	 */
	@Override
	protected SimpleSampleResult execute() {
		SimpleSampleResult res;
		if (CassandraContext.getInstance().clearDB()) {
			res = new SimpleSampleResult("200", "CASSANDRA CLEARED", false);
		} else {
			res = new SimpleSampleResult("400", "ERROR TRYING TO CLEAR", true);
		}
		return res;
	}
}
