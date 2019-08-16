package com.zihong.auth.config;


import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zihong.auth.message.WxLoginReceiver;


@Configuration
public class MessageConfiguration {
	
	public static final String topicExchangeName = "zihong-exchange";

    public static final String queueName = "WxQRCodeLogin";

    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    public WxLoginReceiver receiver() {
        return new WxLoginReceiver();
    }
   

}
