package com.github.yshameer.spring.camel.activemq;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.apache.camel.test.spring.DisableJmx;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@DisableJmx
@ActiveProfiles("test")
@RunWith(CamelSpringBootRunner.class)
@EnableAutoConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = CamelConfig.class)
public class CamelActiveMQAutoConfigTest {


    @Autowired
    private CountDownLatch latch;

    @Produce(uri = "jms:queue:foo")
    private ProducerTemplate producerTemplate;

    @Test
    public void testJmsMessageDelivered() throws Exception {
        producerTemplate.sendBody("Hello, Camel!");
        assertThat(latch.await(1, TimeUnit.SECONDS)).isTrue();
    }
}