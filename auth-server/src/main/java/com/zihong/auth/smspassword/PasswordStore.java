package com.zihong.auth.smspassword;

import com.zihong.auth.smspassword.domain.Account;

public interface PasswordStore {

	Account getAccount(String userName);

	
	Account store(Account auth);

}
