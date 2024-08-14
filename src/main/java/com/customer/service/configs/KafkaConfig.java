package com.customer.service.configs;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;


@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic notificationsTopic() {
        return TopicBuilder.name("notifications-topic").partitions(10).replicas(3).build();
    }
}
