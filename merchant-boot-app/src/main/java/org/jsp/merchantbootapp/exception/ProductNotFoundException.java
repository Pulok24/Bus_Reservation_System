package org.jsp.merchantbootapp.exception;

public class ProductNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4534461058383425638L;

	public ProductNotFoundException(String message) {
		super(message);
	}

}
