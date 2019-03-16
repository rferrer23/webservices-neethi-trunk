package org.apache.neethi.exception;


public class PolicyException extends RuntimeException {

	/**
	 * serial number
	 */
	private static final long serialVersionUID = -8471754846620674556L;

	public PolicyException(String msg, Exception ex) {
		super(msg,ex);
	}

	public PolicyException(String msg) {
		super(msg);
	}

}
