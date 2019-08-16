package com.zihong.auth.smspassword;

import org.springframework.http.HttpStatus;

public enum MessageResp {
	DEFAULT("000", HttpStatus.NO_CONTENT,"无处理"),
	NEW_PASSWORD_SENT("001", HttpStatus.OK, "新的登陆码成功发送"),
	PASSWORD_EXISTED("002", HttpStatus.ALREADY_REPORTED,"登陆码已经发送"), 
	SEND_FAILED("100", HttpStatus.INTERNAL_SERVER_ERROR,"登陆码发送失败!"), 
	SENT_BUT_NOT_STORED("101", HttpStatus.INTERNAL_SERVER_ERROR,"请忽略收到的登陆码,重新请求登陆码.");

	private String code;
	private String message;
	private HttpStatus status;
	
	MessageResp(String code, HttpStatus status, String message){
		this.status = status;
		this.code = code;
		this.message = message;
	}
	
	public HttpStatus status() {
		return this.status;
	}

	public String code() {
		return code;
	}

	public String message() {
		return message;
	}
	
}
