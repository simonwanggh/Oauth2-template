package com.zihong;

import java.security.Principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextListener;

@SpringBootApplication
@RestController
@EnableResourceServer
public class Application  extends SpringBootServletInitializer {
	
	@Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }

    @RequestMapping("/")
    public String home() {
        return "Hello Wealth Way";
    }
    
    @RequestMapping("/user")
    public Principal user(Principal principal) {
      return principal;
    }
    

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
