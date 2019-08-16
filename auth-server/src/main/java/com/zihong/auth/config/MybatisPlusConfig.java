package com.zihong.auth.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
@Configuration
@MapperScan(basePackages = { "com.zihong.auth.dao" })
@EnableTransactionManagement
public class MybatisPlusConfig {

    /**
     * 多租户及分页配置
     *
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }
    
    
    @Bean
    //@Profile({"dev","test"})
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }
    
	
	
	
	@Bean
	public ISqlInjector sqlInjector() {
		return new LogicSqlInjector();
	}

}