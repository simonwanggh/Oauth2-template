package com.zihong.auth.smspassword;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.zihong.auth.smspassword.domain.MiaoDiResponse;

@Service
public class MiaoDiSMSSender implements SMSSender {
	
	@Autowired
	private RestTemplate restTemplate;
	
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	
	private static final String ACCOUNT_SID = "71d920d27e3548b99372c1adfeae4a79";
	private static final String AUTH_TOKEN = "a27a362184ca4c79adc36c38dc47c6ea";
	private static final String MIAO_DI_URL = "http://api.miaodiyun.com/20150822/industrySMS/sendSMS";

	@Override
	public SMSResp sendPassword(String userName,String password) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		LocalDateTime  date = LocalDateTime.now();
		String timestamp = date.format(formatter);
		
		StringBuilder strBuilder = new StringBuilder(ACCOUNT_SID);
		strBuilder.append(AUTH_TOKEN);
		strBuilder.append(timestamp);
		
		String sig = DigestUtils.md5Hex(strBuilder.toString());

		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		map.add("accountSid", ACCOUNT_SID);
		map.add("to", userName);
		map.add("timestamp", timestamp);
		map.add("sig", sig);
		map.add("respDataType", "JSON");
		map.add("templateid", "389151964");
		map.add("param", password+",30");
			
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

		MiaoDiResponse response = restTemplate.postForObject(MIAO_DI_URL, request, MiaoDiResponse.class);
		if(response.getRespCode().equals("00000")) {
			return SMSResp.SUCCESS;
		}else {
			return SMSResp.DEFAULT;
		}
	}

}
