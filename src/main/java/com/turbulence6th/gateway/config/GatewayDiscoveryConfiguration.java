package com.turbulence6th.gateway.config;

import com.turbulence6th.gateway.dao.OrientationDao;
import com.turbulence6th.gateway.entity.Orientation;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class GatewayDiscoveryConfiguration {

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
}
