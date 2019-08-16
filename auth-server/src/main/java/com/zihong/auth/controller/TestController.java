package com.zihong.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zihong.auth.smspassword.SMSPasswordService;

@RestController
public class TestController {
	
	@Autowired
	private SMSPasswordService passwordService;
	
	@RequestMapping("/test")
	public String getPassword(@RequestParam(value="phone")String phoneNumber) {
		return passwordService.getPassword(phoneNumber);
	}

}
