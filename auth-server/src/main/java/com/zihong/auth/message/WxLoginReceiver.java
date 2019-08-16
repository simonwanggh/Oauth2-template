package com.zihong.auth.message;

import java.io.IOException;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zihong.auth.cache.repository.WxLoginUserStore;
import com.zihong.auth.config.MessageConfiguration;
import com.zihong.wx.to.WxCurrentScanUser;

@RabbitListener(queues = MessageConfiguration.queueName)
public class WxLoginReceiver {
	
	@Autowired
	private WxLoginUserStore wxUserStore;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@RabbitHandler
	public void receiveMessage(String message) {
        System.out.println(message);
        if(StringUtils.isEmpty(message)) {
        	return;
        }
        
        try {
			WxCurrentScanUser user = mapper.readValue(message, WxCurrentScanUser.class);
			wxUserStore.store(user);
			//Please save the user to user table if it is not existed in the system
		} catch (Exception e) {
			//Please use log4j or other log api to save it.
			e.printStackTrace();
		}
        
    }

}
