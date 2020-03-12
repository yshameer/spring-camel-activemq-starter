package com.github.yshameer.spring.camel.activemq;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.jms.pool.PooledConnectionFactory;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.component.jms.JmsConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import javax.jms.ConnectionFactory;

@Configuration
@PropertySource("classpath:/camel-activemq-starter-defaults.properties")
@Slf4j
public class CamelActiveMQAutoConfig {

    @Bean
    @ConfigurationProperties("camel.activemq")
    ActiveMQProperties activeMQProperties() {
        return new ActiveMQProperties();
    }

    @Bean
    @Autowired
    @Primary
    public ConnectionFactory activeMqRawConnectionFactory(ActiveMQProperties props) {
        log.info("Connecting to ActiveMQ broker using properties: {}", props);
        return new ActiveMQConnectionFactory(props.getUserName(), props.getPassword(), props.getBrokerUrl());
    }

    @Bean
    @Autowired
    public JmsComponent jms(JmsConfiguration jmsConfiguration) {
        return new JmsComponent(jmsConfiguration);
    }

    @Bean
    @Autowired
    public JmsConfiguration jmsConfiguration(@Qualifier("activeMqRawConnectionFactory") ConnectionFactory connectionFactory,
                                             @Qualifier("activeMqPooledConnectionFactory") ConnectionFactory pooledConnectionFactory) {
        final JmsConfiguration jmsConfiguration = new JmsConfiguration();
        jmsConfiguration.setTemplateConnectionFactory(pooledConnectionFactory);
        jmsConfiguration.setListenerConnectionFactory(connectionFactory);
        return jmsConfiguration;
    }

    @Bean
    @Autowired
    public ConnectionFactory activeMqPooledConnectionFactory(@Qualifier("activeMqRawConnectionFactory") ConnectionFactory connectionFactory,
                                                             ActiveMQProperties props) {
        final PooledConnectionFactory pooled = new PooledConnectionFactory();
        pooled.setBlockIfSessionPoolIsFull(props.isBlockIfSessionPoolIsFull());
        pooled.setBlockIfSessionPoolIsFullTimeout(props.getBlockIfSessionPoolIsFullTimeout());
        pooled.setConnectionFactory(connectionFactory);
        pooled.setCreateConnectionOnStartup(props.isCreateConnectionOnStartup());
        pooled.setExpiryTimeout(props.getExpiryTimeout());
        pooled.setIdleTimeout(props.getIdleTimeout());
        pooled.setMaxConnections(props.getMaxConnections());
        pooled.setMaximumActiveSessionPerConnection(props.getMaximumActiveSessionPerConnection());
        pooled.setReconnectOnException(props.isReconnectOnException());
        pooled.setTimeBetweenExpirationCheckMillis(props.getTimeBetweenExpirationCheckMillis());
        pooled.setUseAnonymousProducers(props.isUseAnonymousProducers());

        return pooled;
    }
}
