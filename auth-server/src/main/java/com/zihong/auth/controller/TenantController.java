package com.zihong.auth.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.zihong.auth.controller.to.Tenant;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/tenants")
public class TenantController {
	
	public static final String CURR_TENANT = "currentTenant";
	
	@RequestMapping(value="/selectedtenant", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void setCurrentTenant(HttpSession session, Tenant tenant) {
		
	}

}
