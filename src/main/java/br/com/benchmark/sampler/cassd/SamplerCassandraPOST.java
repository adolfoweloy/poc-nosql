package br.com.benchmark.sampler.cassd;

import br.com.benchmark.dbcontext.api.DBContext;
import br.com.benchmark.dbcontext.impl.CassandraContext;
import br.com.benchmark.domain.User;
import br.com.benchmark.jmeter.SimpleSampleResult;
import br.com.benchmark.jmeter.TemplateSampler;
import br.com.benchmark.sampler.CassandraIDList;

/**
 * The Class SamplerCassandraPOST.
 * @author astronauta
 */
public class SamplerCassandraPOST extends TemplateSampler {
	
	/**
	 * Instantiates a new sampler cassandra post.
	 */
	public SamplerCassandraPOST() {
		super("cassandra POST");
	}
	

	/* (non-Javadoc)
	 * @see br.com.benchmark.jmeter.TemplateSampler#execute()
	 */
	@Override
	protected SimpleSampleResult execute() {
		CassandraContext context = CassandraContext.getInstance();
		int token = CassandraIDList.getInstance().createToken();
		
		try {
    		context.set(Integer.toString(token), 
    			new User(token, DBContext.CONTENT + ":" + token, DBContext.CONTENT));
		} catch (Exception e) {
			return new SimpleSampleResult("400", "error: " + e.getMessage(), true);
		}
		
		CassandraIDList.getInstance().addRandomId(token);
		return new SimpleSampleResult("200", "success", false);
		
	}
	
}