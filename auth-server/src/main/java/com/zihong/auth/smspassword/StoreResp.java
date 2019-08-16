package com.zihong.auth.smspassword;

public enum StoreResp {
	DEFAULT("000","无处理"),
	SUCCESS("001", "存储成功"),
	FAILED("100", "存储失败");

	private String code;
	private String message;
	
	StoreResp(String code, String message){
		this.code = code;
		this.message = message;
	}

	public String code() {
		return code;
	}

	public String message() {
		return message;
	}

	public boolean isSuccess() {		
		return this.code.startsWith("0");
	}
}
