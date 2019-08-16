package com.zihong.auth.smspassword;

import java.util.List;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.zihong.auth.smspassword.domain.Account;
import com.zihong.auth.smspassword.exception.ArgumentException;

@Service
public class SMSPasswordService {
	
	private static final Logger LOG = LoggerFactory.getLogger(SMSPasswordService.class); 
	
	@Autowired
	private PasswordEncoder  passwordEncoder;
	
	@Autowired
	private List<SMSSender> smsSenders;
	
	@Autowired
	private PasswordStore passwordStore;
	
	@Autowired
	private PasswordGenerator passwordGenerator;
	
	
	public String getPassword(String userName) {
		Account account = passwordStore.getAccount(userName);
		return account == null ? null : account.getPassword();
	}
	
	
	
	public MessageResp sendPassword(String userName) {
		
		if(passwordStore.getAccount(userName) != null) {
			return MessageResp.PASSWORD_EXISTED;
		}
		
		String password = passwordGenerator.generatorPassword();
		
		boolean sendSuccess = false;

		for(SMSSender sender : smsSenders){
			SMSResp resp = sender.sendPassword(userName,password);
			if(resp == SMSResp.SUCCESS) {
				sendSuccess = true;
				break;
			}			
		}
		
		if(!sendSuccess) {
			return MessageResp.SEND_FAILED;
		}
		
		try {
			passwordStore.store(new Account(userName, passwordEncoder.encode(password)));
			return MessageResp.NEW_PASSWORD_SENT;
		}catch(Exception e) {
			LOG.error("存储帐号失败", e);
			return MessageResp.SENT_BUT_NOT_STORED;
		}
		
		
	}
	
	public AuthResp authenticate(String userName, String password, SMSProvider smsProvider) {
		
		Account account = passwordStore.getAccount(userName);
		
		if(account == null) {
			String errMsg = String.format("没有找到为用户%s,配置的帐号！需要重新请求登陆码。", userName);
			LOG.error(errMsg);
			throw new ArgumentException(errMsg);
		}
		
		String encodedPassword = account.getPassword();
		
		if(StringUtils.isEmpty(encodedPassword)) {
			String errMsg = String.format("没有找到为用户%s,配置的登陆码！需要重新请求登陆码。", userName);
			LOG.error(errMsg);
			throw new ArgumentException(errMsg);
		}
		
		boolean isMatched = passwordEncoder.matches(password, encodedPassword);
		
		
		return new AuthResp(isMatched);
	}



}
