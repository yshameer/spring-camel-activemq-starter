package com.github.yshameer.spring.camel.activemq;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

@Validated
@Setter
@Getter
@ToString(exclude = "password")
public class ActiveMQProperties {
    private String brokerUrl;
    private String userName;
    private String password;

    private boolean blockIfSessionPoolIsFull = true;
    private long blockIfSessionPoolIsFullTimeout = -1L;
    private boolean createConnectionOnStartup = true;
    private long expiryTimeout = 0L;
    private int idleTimeout = 30 * 1000;
    private int maxConnections = 5;
    private int maximumActiveSessionPerConnection = 500;
    private boolean reconnectOnException = true;
    private long timeBetweenExpirationCheckMillis = -1L;
    private boolean useAnonymousProducers = true;

}
