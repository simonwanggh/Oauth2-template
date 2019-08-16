package com.zihong.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.zihong.web.feignclient.TrafficClient;

@RestController
@RequestMapping("/traffic")
public class TrafficController {
	
//	@Autowired
//    private OAuth2RestTemplate oAuth2RestTemplate;
	
	@Autowired
    private RestTemplate restTemplate;
	
	@Autowired
    private TrafficClient trafficClient;
	
	@RequestMapping("/accounts/account")
	public String getAccount() {
//		return oAuth2RestTemplate.getForObject("http://localhost:8888/platform/accounts/account", String.class);
		return trafficClient.getAccount();
	}

}
