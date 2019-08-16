package com.zihong.auth.provider;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service("dbUserService")
@Qualifier("dbUserService")
public class DBUserService implements UserDetailsService {
	

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			
			return new User("test","test", Collections.EMPTY_LIST);
		}catch(Exception e) {
			return null;
		}
	}

}
