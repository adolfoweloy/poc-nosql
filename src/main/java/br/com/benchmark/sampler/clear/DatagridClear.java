package br.com.benchmark.sampler.clear;

import br.com.benchmark.jmeter.SimpleSampleResult;
import br.com.benchmark.jmeter.TemplateSampler;
import br.com.benchmark.sampler.DatagridIDList;

/**
 * The Class DatagridClear.
 * @author astronauta
 */
public class DatagridClear extends TemplateSampler {

	/**
	 * Instantiates a new datagrid clear.
	 */
	public DatagridClear() {
		super("datagrid clear");
	}

	/* (non-Javadoc)
	 * @see br.com.benchmark.jmeter.TemplateSampler#execute()
	 */
	@Override
	protected SimpleSampleResult execute() {
		DatagridIDList.getInstance().clear();
		return new SimpleSampleResult("200", "DATAGRID CLEARED", false);
	}
}
