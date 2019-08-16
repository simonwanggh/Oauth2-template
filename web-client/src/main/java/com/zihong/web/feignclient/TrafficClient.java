package com.zihong.web.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

//@FeignClient("zihong-api-gateway")
public interface TrafficClient {
	
	//@RequestMapping("/platform/accounts/account")
	String getAccount();

}
