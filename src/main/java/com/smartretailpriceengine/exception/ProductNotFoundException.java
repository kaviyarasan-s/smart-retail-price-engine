package com.smartretailpriceengine.exception;

public class ProductNotFoundException extends Exception {

	private static final long serialVersionUID = -1467605264918611355L;

	public ProductNotFoundException(String exceptionMessage) {
		super(exceptionMessage);
	}
}
