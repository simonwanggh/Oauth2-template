package com.zihong.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.zihong.web.controller.to.Tenant;

@RestController
@RequestMapping("/tenants")
public class TenantController {
	
//	@Autowired
//    private OAuth2RestTemplate oAuth2RestTemplate;
//	
//	@RequestMapping(value="/selectedtenant", method=RequestMethod.POST)
//	@ResponseStatus(HttpStatus.OK)
//	public void setCurrentTenant(Tenant tenant) {
//		ResponseEntity<Void> response = oAuth2RestTemplate.postForEntity("http://localhost:8081/tenants/selectedtenant",tenant, Void.class);
//		response.getStatusCode();
//	}
//	
//	
//	@RequestMapping(value="/testtenant")
//	@ResponseStatus(HttpStatus.OK)
//	public void setTestCurrentTenant() {
//		Tenant tenant = new Tenant();
//		tenant.setId("tid");
//		tenant.setName("testName");
//		tenant.setDisplayName("testDisplayName");
//		ResponseEntity<Void> response = oAuth2RestTemplate.postForEntity("http://localhost:8081/tenants/selectedtenant",tenant, Void.class);
//		response.getStatusCode();
//	}


}
