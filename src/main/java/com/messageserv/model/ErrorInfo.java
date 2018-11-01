package com.messageserv.model;

public class ErrorInfo {
	
	private String errorMessage;
	private int errorCode;
	private String errorContent;
	
	public ErrorInfo(String errorMessage, int errorCode, String errorContent) {
		super();
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
		this.errorContent = errorContent;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorContent() {
		return errorContent;
	}
	public void setErrorContent(String errorContent) {
		this.errorContent = errorContent;
	}
	

}
