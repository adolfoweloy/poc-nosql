package br.com.benchmark.sampler.del;

import br.com.benchmark.dbcontext.impl.DatagridContext;
import br.com.benchmark.jmeter.SimpleSampleResult;
import br.com.benchmark.jmeter.TemplateSampler;

/**
 * The Class DatagridDelete.
 * 
 * @author astronauta
 */
public class DatagridDelete extends TemplateSampler {

	/**
	 * Instantiates a new datagrid delete.
	 */
	public DatagridDelete() {
		super("datagrid delete");
	}

	/* (non-Javadoc)
	 * @see br.com.benchmark.jmeter.TemplateSampler#execute()
	 */
	@Override
	protected SimpleSampleResult execute() {
		SimpleSampleResult res;
		try {
			DatagridContext.getInstance().clearDB();
			res = new SimpleSampleResult("200", "DATAGRID CLEARED", false);
		} catch (Exception e) {
			res = new SimpleSampleResult("400", "ERROR TRYING TO CLEAR:" + e.getMessage(), true);
		}
		return res;
	}
}
