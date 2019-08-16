package com.zihong.auth.smspassword;

interface SMSSender {
	SMSResp sendPassword(String userName, String password);
}
