package com.zihong.auth.smspassword;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



public class DigitalPasswordGeneratorTest {
	
	private DigitalPasswordGenerator generator;
	
	@Before
	public void setup() {
		generator = new DigitalPasswordGenerator();
	}
	
	@Test
	public void testGeneratorPassword() {
		for(int i = 0 ; i < 100000; i++ ) {
			String password = generator.generatorPassword();

			Assert.assertTrue("Last two chars are not 6 or 8", (password.charAt(4) == '6' || password.charAt(4) == '8')
															&& (password.charAt(5) == '6' || password.charAt(5) == '8'));
			Assert.assertFalse("Password contains 4", password.contains("4"));
			Assert.assertEquals("Password is not 6 digital", 6, password.length());
		}
	}

}
