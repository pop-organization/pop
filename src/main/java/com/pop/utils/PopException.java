package com.pop.utils;

public class PopException extends RuntimeException {
	
	private String exMsg;
	
	public PopException(String exMsg) {
		super(exMsg);
		this.exMsg = exMsg;
	}
		
	public String getExMsg() {
		return exMsg;
	}

	private static final long serialVersionUID = -6284149912805928520L;
	
}
