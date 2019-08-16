package com.zihong.auth.smspassword;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DomainConfig {
	
	@Bean
	public PasswordEncoder passwordEncoderBean(){
		return new BCryptPasswordEncoder();
	}
	
//	@Bean
//	public List<SMSSender> smsSendersList(){
//		List<SMSSender> senders = new ArrayList<>();
//		senders.add(new MiaoDiSMSSender());
//		return senders;
//	}
	
//	@Bean
//	public PasswordStore passwordStoreBean() {
//		return new RedisPasswordStore();
//	}
	
	@Bean
	public PasswordGenerator passwordGenerator() {
		return new DigitalPasswordGenerator();
	}

}
