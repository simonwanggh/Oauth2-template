package com.zihong.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.context.request.RequestContextListener;

@SpringBootApplication
//@EnableDiscoveryClient
//@EnableFeignClients
//@PropertySource("classpath:application-discovery.properties")
public class Application  extends SpringBootServletInitializer {
	
	@Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }    
    

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
