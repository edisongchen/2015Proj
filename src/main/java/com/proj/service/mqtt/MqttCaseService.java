package com.proj.service.mqtt;

import org.springframework.messaging.support.GenericMessage;

public class MqttCaseService {

	public void startCaseChannel(GenericMessage<String> msg){
		System.out.println("==========received from inbound channel========");
		System.out.println(msg.getHeaders().keySet().size()+" :payLoad:"+msg.getPayload());
	}
}
