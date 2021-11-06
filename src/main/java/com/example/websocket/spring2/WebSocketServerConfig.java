package com.example.websocket.spring2;

import com.example.websocket.spring1.WebSocketConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@EnableWebSocket
@Configuration
public class WebSocketServerConfig implements WebSocketConfigurer {
    @Autowired
    private WebSocketServerHandler webSocketServerHandler;
    @Autowired
    private WebSocketServerInterceptor webSocketServerInterceptor;

    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketServerHandler, "websocket/server2")
                .addInterceptors(webSocketServerInterceptor)
                .setAllowedOrigins("*");
    }
}
