package com.turbulence6th.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import javax.net.ssl.SSLContext;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class SslConfiguration {

    @Bean
    public WebSocketClient webSocketClient() throws NoSuchAlgorithmException {
        StandardWebSocketClient simpleWebSocketClient = new StandardWebSocketClient();
        Map<String, Object> userProperties = new HashMap<>();
        userProperties.put("org.apache.tomcat.websocket.SSL_CONTEXT", SSLContext.getDefault());
        simpleWebSocketClient.setUserProperties(userProperties);
        return simpleWebSocketClient;
    }
}
