package com.zihong.auth.smspassword.exception;

public class ArgumentException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7290066695805104609L;

	public ArgumentException() {
		super();
	}

	public ArgumentException(String message) {
		super(message);
		
	}

	public ArgumentException(Throwable cause) {
		super(cause);
		
	}

	public ArgumentException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public ArgumentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

}
