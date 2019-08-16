package com.zihong.auth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CommonConfig {
	
	@Value("${restclient.timeout}")
	private int timeout;
	
	@Bean
	public RestTemplate restTemplateBean() {
		return new RestTemplate(getClientHttpRequestFactory());
	}
	
	@Bean
	public RetryTemplate retryTemplateBean() {
		return new RetryTemplate();
	}

	
	private ClientHttpRequestFactory getClientHttpRequestFactory() {
	    HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
	    factory.setReadTimeout(timeout);
	    factory.setConnectTimeout(timeout);
	    return factory;
	}

}
