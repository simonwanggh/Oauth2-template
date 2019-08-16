package com.zihong.auth.smspassword;

public class AuthResp {
	
	private boolean success;
	
	public AuthResp(boolean success) {
		this.success = success;
	}

	public boolean isSuccess() {
		return success;
	}
	
	

}
