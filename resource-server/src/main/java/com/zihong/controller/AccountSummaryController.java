package com.zihong.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1.0/accounts")
public class AccountSummaryController {
	
	@RequestMapping("/account")
	public String getAccount() {
		return "testing...... Taffic";
	}

}
