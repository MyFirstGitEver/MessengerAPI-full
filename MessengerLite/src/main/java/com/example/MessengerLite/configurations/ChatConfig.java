package com.example.MessengerLite.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class ChatConfig implements WebSocketMessageBrokerConfigurer
{

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) 
	{
		registry.addEndpoint("/ws").setAllowedOriginPatterns("*"); // connection point
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) 
	{
		registry.setApplicationDestinationPrefixes("/app"); // sender point
		registry.enableSimpleBroker("/user"); // listener point
	}
}