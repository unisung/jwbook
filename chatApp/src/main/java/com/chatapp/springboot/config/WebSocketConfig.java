package com.chatapp.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{

	// WebSocket 연결용 endpoint 정의 (SockJS fallback 포함)
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/ws")  // 연결 엔드포인트 (예: /ws)
		        .setAllowedOriginPatterns("*")
		        .withSockJS(); // 브라우저 호환성 보장 (SockJS fallback)
	}
	
	// 클라이언트가 메시지를 보낼 prefix 설정 (예: /app/send)
	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.setApplicationDestinationPrefixes("/app"); // 메시지 발행 prefix, /app/~
        config.enableSimpleBroker("/topic"); // 구독 주소 prefix (브로커 주소) /topic
	}
	
}
