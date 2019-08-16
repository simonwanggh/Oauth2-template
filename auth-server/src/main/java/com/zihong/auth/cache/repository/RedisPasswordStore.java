package com.zihong.auth.cache.repository;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.zihong.auth.smspassword.PasswordStore;
import com.zihong.auth.smspassword.domain.Account;

@Repository
class RedisPasswordStore implements PasswordStore {

	@Cacheable(value = "smsPasswordCache", key = "#userName")
	@Override
	public Account getAccount(String userName) {		
		return null;
	}

	
	@CachePut(value = "smsPasswordCache", key = "#account.userName")
	@Override
	public Account store(Account account) {
		return account;
	}

}
