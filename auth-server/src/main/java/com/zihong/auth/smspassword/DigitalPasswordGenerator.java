package com.zihong.auth.smspassword;

import java.util.Random;

class DigitalPasswordGenerator implements PasswordGenerator {
	
	private char[] header_sources = {'0','1','2','3','5','6','7','8','9'};
	private char[] footer_sources = {'6','8'};
	private static final int HEADER_UPPER_BOUND = 9;
	private static final int FOOTER_UPPER_BOUND = 2;
	private static final int PASSWORD_HEADER_LENGTH = 4;
	private static final int PASSWORD_FOOTER_LENGTH = 2;

	/**
	 * Generate 6 digits password , the last two chars are 6 or 8
	 */
	@Override
	public String generatorPassword() {
		Random r = new Random();
		
		StringBuilder strBuilder = new StringBuilder();
		
		for(int i = 0 ; i < PASSWORD_HEADER_LENGTH; i++) {
			strBuilder.append(header_sources[r.nextInt(HEADER_UPPER_BOUND)]);
		}
		
		for(int i = 0 ; i < PASSWORD_FOOTER_LENGTH; i++) {
			strBuilder.append(footer_sources[r.nextInt(FOOTER_UPPER_BOUND)]);
		}
		
		
		return strBuilder.toString();
	}

}
