package br.com.benchmark.sampler.dgrid;

import br.com.benchmark.dbcontext.api.DBContext;
import br.com.benchmark.dbcontext.impl.DatagridContext;
import br.com.benchmark.domain.User;
import br.com.benchmark.jmeter.SimpleSampleResult;
import br.com.benchmark.jmeter.TemplateSampler;
import br.com.benchmark.sampler.DatagridIDList;


/**
 * The Class SamplerDatagridPOST.
 * @author astronauta
 */
public class SamplerDatagridPOST extends TemplateSampler {
	
	/**
	 * Instantiates a new sampler datagrid post.
	 */
	public SamplerDatagridPOST() {
		super("datagrid POST");
	}

	/* (non-Javadoc)
	 * @see br.com.benchmark.jmeter.TemplateSampler#execute()
	 */
	@Override
	protected SimpleSampleResult execute() {
		DBContext context = DatagridContext.getInstance();
		int token = DatagridIDList.getInstance().createToken();
		
		try {
    		context.set(Integer.toString(token), 
    			new User(token, DBContext.CONTENT + ":" + token, DBContext.CONTENT));
		} catch (Exception e) {
			return new SimpleSampleResult("400", "error: " + e.getMessage(), true);
		}
		
		DatagridIDList.getInstance().addRandomId(token);
		return new SimpleSampleResult("200", "success", false);
		
	}

}
