package com.zihong.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/accounts")
public class AccountSummaryController {
	
	@RequestMapping("/account")
	public String getAccount() {
		return "testing......";
	}

}
