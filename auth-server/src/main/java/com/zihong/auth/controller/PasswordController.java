package com.zihong.auth.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.zihong.auth.smspassword.MessageResp;
import com.zihong.auth.smspassword.SMSPasswordService;

@RestController
public class PasswordController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${wechat.qrCodeUrl}")
	private String qrCodeUrl;
	
	@Autowired
	private SMSPasswordService passwordService;
	
	@RequestMapping("/basic/sms-pass-msgs")
	public ResponseEntity<String> sendPasswordMessage(@RequestParam(value="phone") String phoneNumber) {
		MessageResp resp = passwordService.sendPassword(phoneNumber);
		
		return ResponseEntity.status(resp.status()).body(resp.message());		
	}
	
	
	@RequestMapping("/basic/qrcode")
	public String getQrCode(HttpServletRequest request) {
		String sessionId = request.getSession().getId();
		Map params = new HashMap();
		params.put("scene", sessionId);
		return restTemplate.getForObject(qrCodeUrl, String.class, params);		
	}

}
