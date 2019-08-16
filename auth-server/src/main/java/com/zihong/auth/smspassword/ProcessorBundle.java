package com.zihong.auth.smspassword;

import org.springframework.security.crypto.password.PasswordEncoder;

public interface ProcessorBundle {

	PasswordStore getPasswordStrore();

	PasswordEncoder getPasswordEncoder();

}
