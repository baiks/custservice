package com.customer.service.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;  

@Service  
public class Consumer {

    @KafkaListener(topics = "cust-topic")
    public void listen(String message) {  
        System.out.println("Received message: " + message);  
    }  
}