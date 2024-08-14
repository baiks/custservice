package com.customer.service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;  
import org.springframework.stereotype.Service;  

@Service  
public class Producer {

    private final KafkaTemplate<String, String> kafkaTemplate;  

    @Autowired  
    public Producer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;  
    }  

    public void sendMessage(String topic, String message) {  
        kafkaTemplate.send(topic, message);  
    }  
}  