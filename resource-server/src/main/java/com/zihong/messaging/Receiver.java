package com.zihong.messaging;

import org.springframework.stereotype.Component;

@Component
public class Receiver {
	
	public void receiveMessage(Object message) {
        System.out.println("Received <" + new String((byte[])message) + ">  " );      
    }

}
