package com.spring.files.springbootfileassignment.model;

public class ErrorData {

	private String status;
	private String message;
	private String timeEventIncurred;

	public ErrorData() {

	}

	public ErrorData(String status, String message, String timeEventIncurred) {
		this.status = status;
		this.message = message;
		this.timeEventIncurred = timeEventIncurred;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTimeEventIncurred() {
		return timeEventIncurred;
	}

	public void setTimeEventIncurred(String timeEventIncurred) {
		this.timeEventIncurred = timeEventIncurred;
	}
}
