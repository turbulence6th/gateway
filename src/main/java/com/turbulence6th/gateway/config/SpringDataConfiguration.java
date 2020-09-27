package com.turbulence6th.gateway.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = "com.turbulence6th.gateway.entity")
@EnableJpaRepositories(basePackages = "com.turbulence6th.gateway.dao")
@Configuration
public class SpringDataConfiguration {
}
