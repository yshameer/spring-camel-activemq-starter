# Spring Camel/ActiveMQ Starter

The Spring Camel/ActiveMQ Starter extends the [Camel Spring Boot](http://camel.apache.org/spring-boot.html) support to automatically create a JmsComponent configured for a particular ActiveMQ broker.

## Installation

The Spring Camel/ActiveMQ Starter is a Spring Boot Auto-configuration library, so all you need to do is add it to your classpath:

```xml
<dependency>
  <groupId>com.shameer</groupId>
  <artifactId>spring-camel-activemq-starter</artifactId>
  <version>${spring.camel.activemq.starter.version}</version>
</dependency>
```

## Configuration Properties    

Name                                     | Description   | Default Value
-----------------------------------------|---------------|--------------:
camel.activemq.brokerUrl                | the broker URL  |  failover:(tcp://localhost:61616)?jms.prefetchPolicy.all=1&randomize=false   
camel.activemq.userName            | the username | admin
camel.activemq.password           | password  | admin
camel.activemq.blockIfSessionPoolIsFull | controls the behavior of the internal session pool. By default the call to Connection.getSession() will block if the session pool is full. If the argument false is given, it will change the default behavior and instead the call to getSession() will throw a JMSException.  | true
camel.activemq.blockIfSessionPoolIsFullTimeout | controls how long to wait (in milliseconds) for a session if the session pool is full (and blockIfSessionPoolIsFull is true) | -1 (disabled)
camel.activemq.expiryTimeout | the configured expiration timeout for connections in the pool  | 0
camel.activemq.idleTimeout | the idle timeout value applied to new Connection's that are created by this pool  | 30,000
camel.activemq.maxConnections  |  the maximum number of connections to maintain in the pool  | 5
camel.activemq.maximumActiveSessionPerConnection | the maximum number of sessions a pooled Connection will create before it either blocks or throws an exception when a new session is requested, depending on configuration  | 500
camel.activemq.reconnectOnException | true if the underlying connection will be renewed on JMSException, false otherwise  | true
camel.activemq.timeBetweenExpirationCheckMillis |  the number of milliseconds to sleep between runs of the idle Connection eviction thread  | -1 (disabled)
camel.activemq.useAnonymousProducers | whether a PooledSession uses only one anonymous MessageProducer instance or creates a new MessageProducer for each call the create a MessageProducer | true
