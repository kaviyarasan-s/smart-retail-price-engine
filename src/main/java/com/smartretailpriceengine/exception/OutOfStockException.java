package com.smartretailpriceengine.exception;

public class OutOfStockException extends Exception {

	private static final long serialVersionUID = 1538111120061009101L;

	public OutOfStockException(String exMessage) {
		super(exMessage);
	}

}
