package com.proj.controller.mqtt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proj.common.util.StringUtils;

@Controller
@RequestMapping("/mqtt")
public class MqttControllerTest {
	@Autowired
	private MqttPahoMessageHandler outHandler;
	
	@Autowired
	private MqttPahoMessageDrivenChannelAdapter signalTopicsAdapter;
	
	@RequestMapping("/pub")
	public void subTopic(String msg,String topic,String name){
		System.out.println(outHandler);
		/*if (StringUtils.isNotEmpty(topic)) {
			outHandler.setDefaultTopic(topic);
		}
		outHandler.setDefaultRetained(false);
		System.out.println("will send message:"+msg);
		outHandler.handleMessage(new GenericMessage<String>(msg));*/
		Message<String> message =MessageBuilder.withPayload(msg)
				.setHeader(MqttHeaders.TOPIC, topic).build();
		outHandler.handleMessage(message);
		System.out.println("//////////");
	}
	
	@RequestMapping("/sub")
	public void subTopic2(String topic){
		if (StringUtils.isNoneEmpty(topic)) {
			signalTopicsAdapter.addTopic(topic);
			String[] topics = signalTopicsAdapter.getTopic();
		}
		System.out.println("//////////");
	}
	
}
