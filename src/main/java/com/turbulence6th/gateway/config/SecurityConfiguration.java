package com.turbulence6th.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class SecurityConfiguration {

    @Value("${server.ssl.enabled:false}")
    private boolean sslEnabled;

    @PostConstruct
    public void startRedirectServer() {
        if (sslEnabled) {
            NettyReactiveWebServerFactory httpNettyReactiveWebServerFactory = new NettyReactiveWebServerFactory(8080);
            httpNettyReactiveWebServerFactory.getWebServer((request, response) -> {
                URI uri = request.getURI();
                URI httpsUri;
                try {
                    httpsUri = new URI("https", uri.getUserInfo(), uri.getHost(), 443, uri.getPath(), uri.getQuery(), uri.getFragment());
                } catch (URISyntaxException e) {
                    return Mono.error(e);
                }
                response.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
                response.getHeaders().setLocation(httpsUri);
                return response.setComplete();
            }).start();
        }
    }
}
