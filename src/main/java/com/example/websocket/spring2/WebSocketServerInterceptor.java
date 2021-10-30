package com.example.websocket.spring2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

@Component
public class WebSocketServerInterceptor implements HandshakeInterceptor {
    private Logger logger = LoggerFactory.getLogger(WebSocketServerInterceptor.class);

    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        logger.info("-----server beforeHandshake");
        logger.info("-----server beforeHandshake request Headers :{}",request.getHeaders());
        logger.info("-----server beforeHandshake: attributes{}",attributes);
        return true; // 默认 false ，会报 DeploymentException: The HTTP response from the server [200] did not permit the HTTP upgrade to WebSocket 错误
    }

    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
        logger.info("-----server afterHandshake");
    }
}
