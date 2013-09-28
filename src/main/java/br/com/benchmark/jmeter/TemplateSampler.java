package br.com.benchmark.jmeter;

import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

/**
 * Template method for JMeter samplers.
 * @author astronauta
 */
public abstract class TemplateSampler extends AbstractJavaSamplerClient {
	private String sampleLabel = "Default label";
	
	protected TemplateSampler(String sampleLabel) { this.sampleLabel = sampleLabel; }
	
	@Override
	public SampleResult runTest(JavaSamplerContext ctx) {
		SampleResult result = new SampleResult();
		result.sampleStart();
		
		result.setSampleLabel(sampleLabel);
		result.setDataType(SampleResult.TEXT);
		
		SimpleSampleResult ssr = execute();
		
		result.setResponseCode(ssr.getCode());
		result.setResponseMessage(ssr.getMessage());
		result.setSuccessful(!ssr.hasError());
		
		result.sampleEnd();
		return result;
	}
	
	/**
	 * Method that needs to be implemented with your business logic.
	 * @return SimpleSampleResult
	 */
	protected abstract SimpleSampleResult execute();


}