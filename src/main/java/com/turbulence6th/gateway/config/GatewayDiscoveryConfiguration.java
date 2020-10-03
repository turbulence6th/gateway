package com.turbulence6th.gateway.config;

import com.turbulence6th.gateway.dao.OrientationDao;
import com.turbulence6th.gateway.entity.Orientation;
import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class GatewayDiscoveryConfiguration {

    @Value( "${turbulence6th.http.port}" )
    private Integer httpPort;


    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder, OrientationDao orientationDao) {
        RouteLocatorBuilder.Builder b = builder.routes();

        List<Orientation> orientations = orientationDao.findAll();
        for (Orientation orientation : orientations) {
            b = b.route(r -> r.host(orientation.getDomain())
                    .and()
                    .path("/**")
                    .uri(orientation.getUrl()));
        }

        return  b.build();
    }

    @Bean
    public ConfigurableServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
        connector.setPort(httpPort);
        factory.addAdditionalTomcatConnectors(connector);
        return factory;
    }
}
