package com.zihong.auth.smspassword.domain;

import java.io.Serializable;

public class Account implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7914919668267482086L;
	private String userName;
	private String password;
	
	public Account(String account, String password) {
		this.userName = account;
		this.password = password;
	}
	
	
	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
