package com.pop.dto;

public class ErrorOutPutDto {
	
	private String errorMsg;
	
	public ErrorOutPutDto (String errorMsg){
		this.errorMsg = errorMsg;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
