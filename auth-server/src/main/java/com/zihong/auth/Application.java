package com.zihong.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(factory = YamlPropertySourceFactory.class, value ="classpath:application-evn-based.yml")
@EnableCaching
public class Application extends SpringBootServletInitializer  {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
