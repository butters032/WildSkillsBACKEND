package com.teamwiski.wildskills.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    // This method configures the message broker for WebSocket
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Enable a simple message broker to carry messages back to the client
        registry.enableSimpleBroker("/topic", "/queue"); // destinations with prefix "/topic" or "/queue" for broadcasting
        registry.setApplicationDestinationPrefixes("/app"); // prefix for messages sent from clients to controllers
    }

    // This method registers the WebSocket endpoint where clients will connect
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Register the "/ws" endpoint for WebSocket connections, and enable SockJS for fallback support
        registry.addEndpoint("/ws").withSockJS();
    }
}