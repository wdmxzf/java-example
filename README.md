# java-example
本分支主要描述的是spring 中两种Websocket 实现（客户端和服务端）

两种Server端（AbstractWebSocketHandler和@ServerEndpoint）
两种Client 端（StandardWebSocketClient和WebSocketContainer）

最近项目中需要使用WebSocket实现实时传递消息功能，网上大多数都是 Socket 实现，而 使用Spring 的WebSocket 的也有不少，但是大多数都只写了一种方法：@ServerEndpoint() 这个方法 ，其实还有另一种方法 WebSocketConfigurer 。这里将会把两种方法介绍下。

# @ServerEndpoint() 方法

## 服务端配置

*   WebSocket 配置类

```java
@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
```

*   服务端类
```java
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
```
## 客户端
```java
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
```
## 连接方法
```java
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        ClientEndpointConfig clientEndpointConfig = ClientEndpointConfig.Builder
                .create().configurator(new ClientEndpointConfig.Configurator(){
                    @Override
                    public void beforeRequest(Map<String, List<String>> headers) {
                        super.beforeRequest(headers);
                        List<String> values = new ArrayList<String>();
                        values.add("v"); // header value
                        headers.put("k",values);// header
                    }
                }).build();
        Session session = container.connectToServer(WebSocketClient.class, clientEndpointConfig, URI.create("ws://localhost:8181/websocket/server1"));
```
*   要想忽略ssl认证，则需要添加SSLContext
```java
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

```
> x509ExtendedTrustManager 必须是 com.sun.net.ssl.internal.ssl.X509ExtendedTrustManager 下的包。

## 连接后Log 信息
```
2021-10-30 11:17:44.515  INFO 15808 --- [nio-8181-exec-1] c.e.websocket.spring1.WebSocketClient    : -----client open
2021-10-30 11:17:44.518  INFO 15808 --- [nio-8181-exec-2] c.e.websocket.spring1.WebSocketServer    : -----server open
2021-10-30 11:17:44.532  INFO 15808 --- [nio-8181-exec-3] c.e.websocket.spring1.WebSocketServer    : -----server message:ABC
2021-10-30 11:17:44.537  INFO 15808 --- [lient-AsyncIO-1] c.e.websocket.spring1.WebSocketClient    : -----client message:DEF
2021-10-30 11:17:44.538  INFO 15808 --- [lient-AsyncIO-1] c.e.websocket.spring1.WebSocketClient    : -----client close
2021-10-30 11:17:44.538  INFO 15808 --- [nio-8181-exec-4] c.e.websocket.spring1.WebSocketServer    : -----server close
```
# WebSocketConfigurer 方法

## 服务端配置

*   AbstractWebSocketHandler 实现，主要是接收消息
```java
@Component
public class WebSocketServerHandler extends AbstractWebSocketHandler {
    private Logger logger = LoggerFactory.getLogger(WebSocketServerHandler.class);
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        logger.info("-----server open");
    }
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);

        logger.info("-----server close");
    }
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        super.handleTransportError(session, exception);
        logger.error("-----server error", exception);
    }
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        logger.info("-----server text message:{}", message.getPayload());
        if ("ABC".equals(message.getPayload())){
            session.sendMessage(new TextMessage("DEF"));
        }
    }
    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {
        super.handleBinaryMessage(session, message);
        logger.info("-----server binary message:{}", message.getPayload());
    }
    @Override
    protected void handlePongMessage(WebSocketSession session, PongMessage message) throws Exception {
        super.handlePongMessage(session, message);
        logger.info("-----server pong message:{}", message.getPayload());
    }
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        super.handleMessage(session, message);
        logger.info("-----server message:{}", message.getPayload());
    }
}
```
* HandshakeInterceptor 实现，主要作用是对请求的拦截
```java
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
```
*   WebSocket 配置，主要是配置跨域、请求路径等
```java
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
```
## 客户端

*   AbstractWebSocketHandler实现，主要作用是接收数据，连接监听等。
```java
@Component
public class WebSocketClientHandler extends AbstractWebSocketHandler {
    private Logger logger = LoggerFactory.getLogger(WebSocketClientHandler.class);

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        logger.info("-----client open");
        session.sendMessage(new TextMessage("ABC"));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);

        logger.info("-----client close");
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        super.handleTransportError(session, exception);
        logger.error("-----client error", exception);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        logger.info("-----client text message:{}", message.getPayload());
        if ("DEF".equals(message.getPayload())){
            session.close();
        }
    }

    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {
        super.handleBinaryMessage(session, message);
        logger.info("-----client binary message:{}", message.getPayload());
    }

    @Override
    protected void handlePongMessage(WebSocketSession session, PongMessage message) throws Exception {
        super.handlePongMessage(session, message);
        logger.info("-----client pong message:{}", message.getPayload());
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        super.handleMessage(session, message);
        logger.info("-----client message:{}", message.getPayload());
        // 所有message 都会运行到这里，一般情况不重写。
    }
}
```
## 连接方法
```java
        StandardWebSocketClient standardWebSocketClient = new StandardWebSocketClient();
        WebSocketConnectionManager webSocketConnectionManager = new WebSocketConnectionManager(standardWebSocketClient, webSocketClientHandler, "ws://localhost:8181/websocket/server2");
        webSocketConnectionManager.setOrigin("*");
        HttpHeaders headers = new HttpHeaders();
        headers.set("k", "v");
        webSocketConnectionManager.setHeaders(headers);
        webSocketConnectionManager.start();

```
## 连接后Log信息
```java
2021-10-30 11:12:20.997  INFO 15779 --- [nio-8181-exec-1] o.s.w.s.c.WebSocketConnectionManager     : Starting WebSocketConnectionManager
2021-10-30 11:12:20.997  INFO 15779 --- [nio-8181-exec-1] o.s.w.s.c.WebSocketConnectionManager     : Connecting to WebSocket at ws://localhost:8181/websocket/server2
2021-10-30 11:12:26.049  INFO 15779 --- [nio-8181-exec-2] c.e.w.s.WebSocketServerInterceptor       : -----server beforeHandshake
2021-10-30 11:12:26.050  INFO 15779 --- [nio-8181-exec-2] c.e.w.s.WebSocketServerInterceptor       : -----server beforeHandshake request Headers :[sec-websocket-key:"C4QJYOfTt266k/88DfM6Lg==", connection:"upgrade", sec-websocket-version:"13", host:"localhost:8181", k:"v", upgrade:"websocket"]
2021-10-30 11:12:26.052  INFO 15779 --- [nio-8181-exec-2] c.e.w.s.WebSocketServerInterceptor       : -----server beforeHandshake: attributes{}
2021-10-30 11:12:26.067  INFO 15779 --- [nio-8181-exec-2] c.e.w.s.WebSocketServerInterceptor       : -----server afterHandshake
2021-10-30 11:12:26.091  INFO 15779 --- [nio-8181-exec-2] c.e.w.spring2.WebSocketServerHandler     : -----server open
2021-10-30 11:12:26.091  INFO 15779 --- [cTaskExecutor-1] c.e.w.spring2.WebSocketClientHandler     : -----client open
2021-10-30 11:12:26.109  INFO 15779 --- [cTaskExecutor-1] o.s.w.s.c.WebSocketConnectionManager     : Successfully connected
2021-10-30 11:12:26.110  INFO 15779 --- [nio-8181-exec-3] c.e.w.spring2.WebSocketServerHandler     : -----server text message:ABC
2021-10-30 11:12:26.114  INFO 15779 --- [lient-AsyncIO-2] c.e.w.spring2.WebSocketClientHandler     : -----client text message:DEF
2021-10-30 11:12:26.114  INFO 15779 --- [nio-8181-exec-3] c.e.w.spring2.WebSocketServerHandler     : -----server message:ABC
2021-10-30 11:12:26.114  INFO 15779 --- [lient-AsyncIO-2] c.e.w.spring2.WebSocketClientHandler     : -----client close
2021-10-30 11:12:26.114  INFO 15779 --- [lient-AsyncIO-2] c.e.w.spring2.WebSocketClientHandler     : -----client message:DEF
2021-10-30 11:12:26.115  INFO 15779 --- [nio-8181-exec-4] c.e.w.spring2.WebSocketServerHandler     : -----server close
```
# 总结

`@ServerEndpoint()` 这种方式的，操作简单，方法封装的很好，但是不能拦截 WebSocket 的请求，也就不能活去header 值，加入需要校验，那么就会不知所措。
`WebSocketConfigurer` 这种方法，操作起来稍微复杂一些，但是可以自己设置拦截器，拦截请求，能获取到请求中的所有的内容。
他们都各有利弊，根据自己项目的实际情况来选择使用哪种方式。
