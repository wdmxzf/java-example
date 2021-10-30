package com.example.websocket;

import com.example.websocket.spring1.WebSocketClient;
import com.example.websocket.spring2.WebSocketClientHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 */
@RestController
@RequestMapping("/connection")
public class SocketController {

    @Autowired
    private WebSocketClientHandler webSocketClientHandler;

    // http://localhost:8181/connection/socket1
    @RequestMapping("/socket1")
    public String connectSocket1() throws IOException, DeploymentException {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        ClientEndpointConfig clientEndpointConfig = ClientEndpointConfig.Builder
                .create().configurator(new ClientEndpointConfig.Configurator(){
                    @Override
                    public void beforeRequest(Map<String, List<String>> headers) {
                        super.beforeRequest(headers);
                        List<String> values = new ArrayList<String>();
                        values.add("v");
                        headers.put("k",values);
                    }
                }).build();
        Session session = container.connectToServer(WebSocketClient.class, clientEndpointConfig, URI.create("ws://localhost:8181/websocket/server1"));
        return "connection successful";
    }

    // http://localhost:8181/connection/socket2
    @RequestMapping("/socket2")
    public String connectSocket2(){
        StandardWebSocketClient standardWebSocketClient = new StandardWebSocketClient();
        WebSocketConnectionManager webSocketConnectionManager = new WebSocketConnectionManager(standardWebSocketClient, webSocketClientHandler, "ws://localhost:8181/websocket/server2");
        webSocketConnectionManager.setOrigin("*");
        HttpHeaders headers = new HttpHeaders();
        headers.set("k", "v");
        webSocketConnectionManager.setHeaders(headers);
        webSocketConnectionManager.start();
        return "connection successful";
    }

}
