package br.com.benchmark.jmeter;

/**
 * The Class SimpleSampleResult.
 * Defines simple data result for JMeter purposes. 
 * @author astronauta
 */
public class SimpleSampleResult {
	
	/** The code. */
	private String code;
	
	/** The message. */
	private String message;
	
	/** The error. */
	private boolean error;
	
	/**
	 * Instantiates a new simple sample result.
	 *
	 * @param code the code
	 * @param message the message
	 * @param error the error
	 */
	public SimpleSampleResult(String code, String message, boolean error) {
		super();
		this.code = code;
		this.message = message;
		this.error = error;
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code the new code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Checks for error.
	 *
	 * @return true, if successful
	 */
	public boolean hasError() {
		return error;
	}

	/**
	 * Sets the error.
	 *
	 * @param error the new error
	 */
	public void setError(boolean error) {
		this.error = error;
	}
	
}