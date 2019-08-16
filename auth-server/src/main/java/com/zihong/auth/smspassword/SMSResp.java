package com.zihong.auth.smspassword;

public enum SMSResp {
	DEFAULT("000","无处理"),
	SUCCESS("001", "登陆码成功发送"),
	FAILED("100", "发送失败");

	private String code;
	private String message;

	
	SMSResp(String code, String message){
		this.code = code;
		this.message = message;
	}

	public String code() {
		return code;
	}

	public String message() {
		return message;
	}	
	
}
