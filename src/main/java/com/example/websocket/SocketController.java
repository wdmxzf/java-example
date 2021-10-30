package com.example.websocket;

import com.example.websocket.spring1.WebSocketClient;
import com.example.websocket.spring2.WebSocketClientHandler;
import com.sun.net.ssl.internal.ssl.X509ExtendedTrustManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import sun.rmi.rmic.iiop.Constants;

import javax.net.ssl.SSLContext;
import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.apache.tomcat.websocket.Constants.SSL_CONTEXT_PROPERTY;

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

    private void connectIgnoreSSL() throws IOException, DeploymentException, NoSuchAlgorithmException, KeyManagementException {
        X509ExtendedTrustManager x509ExtendedTrustManager = new X509ExtendedTrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s, String s1, String s2) throws CertificateException {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s, String s1, String s2) throws CertificateException {

            }

            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

            }

            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

            }

            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };

        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, new X509ExtendedTrustManager[]{x509ExtendedTrustManager},new SecureRandom());

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
        clientEndpointConfig.getUserProperties().put(SSL_CONTEXT_PROPERTY, sslContext);
        Session session = container.connectToServer(WebSocketClient.class, clientEndpointConfig, URI.create("ws://localhost:8181/websocket/server1"));

    }

}
