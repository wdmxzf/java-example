package com.example.websocket.spring1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.nio.ByteBuffer;

@Component
@ServerEndpoint(value ="/websocket/server1")
public class WebSocketServer {
    private Logger logger = LoggerFactory.getLogger(WebSocketServer.class);
    @OnOpen
    public void onOpen(Session session) {
        logger.info("-----server open");

    }

    @OnMessage
    public void onMessage(Session session, String message) throws IOException {
        logger.info("-----server message:{}", message);
        if ("ABC".equals(message)){
            session.getBasicRemote().sendText("DEF");
        }
    }

    @OnMessage
    public void onBinaryMessage(ByteBuffer buffer, Session session){
        logger.info("-----server binary message:{}", buffer);
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason){
        logger.info("-----server close");
    }

    @OnError
    public void onError(Session session, Throwable throwable){
        logger.error("-----server error", throwable);
    }

}
