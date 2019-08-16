package com.zihong.auth.provider;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.zihong.auth.smspassword.SMSPasswordService;

@Service("phoneUserService")
@Qualifier("phoneUserService")
public class PhoneUserService implements UserDetailsService {
	
	@Autowired
	private SMSPasswordService passwordService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			String storedPassword =  passwordService.getPassword(username);
			if(StringUtils.isEmpty(storedPassword)) {
				throw new UsernameNotFoundException("用户不存在");
			}
			return new User(username,storedPassword, Collections.EMPTY_LIST);
		}catch(UsernameNotFoundException e) {
			throw e;
		}catch(Exception e) {
			//please add log
			return null;
		}
	}

}
