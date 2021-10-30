package com.example.websocket.spring1;

import com.example.websocket.spring2.WebSocketClientHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;

import javax.websocket.*;
import java.io.IOException;

/**
 * 方式一WebSocket 客户端
 */
public class WebSocketClient extends Endpoint {
    private Logger logger = LoggerFactory.getLogger(WebSocketClient.class);

    @Override
    public void onClose(Session session, CloseReason closeReason) {
        super.onClose(session, closeReason);
        logger.info("-----client close");
    }

    @Override
    public void onError(Session session, Throwable throwable) {
        super.onError(session, throwable);
        logger.error("-----client error", throwable);
    }

    @Override
    public void onOpen(final Session session, EndpointConfig endpointConfig) {
        logger.info("-----client open");
        session.addMessageHandler(new MessageHandler.Whole<String>() {
            public void onMessage(String s) {
                logger.info("-----client message:{}", s);
                try {
                    onHandleMessage(session, s);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        try {
            session.getBasicRemote().sendText("ABC");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void onHandleMessage(Session session, String message) throws IOException {
        if ("DEF".equals(message)){
            session.close();
        }
    }
}
