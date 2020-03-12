package com.github.yshameer.spring.camel.activemq;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.CountDownLatch;

@Configuration
public class CamelConfig {

    @Bean
    public CountDownLatch countDownLatch() {
        return new CountDownLatch(1);
    }

    @Bean
    public RouteBuilder routeBuilder(CountDownLatch latch) {
        return new RouteBuilder() {
            @Override
            public void configure() {
                from("jms:queue:foo").process(exchange -> latch.countDown());
            }
        };
    }
}
