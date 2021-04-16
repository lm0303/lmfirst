package com.liubin.config;

import javax.jms.Queue;
import javax.jms.Topic;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActiveMqConfig {
    @Value("${my.active.queue}")
    private String queueName;

    @Value("${my.active.topic}")
    private String topicName;

    @Bean
    public Queue queue() {
        return new ActiveMQQueue(queueName);
    }

    // Topic模式下的Destination
    @Bean
    public Topic topic() {
        return new ActiveMQTopic(topicName);
    }
}
