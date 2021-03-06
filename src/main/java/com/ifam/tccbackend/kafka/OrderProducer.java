package com.ifam.tccbackend.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@Component
public class OrderProducer {

    @Value("${order.new-register}")
    private String orderTopic;

    private final KafkaTemplate kafkaTemplate;

    public OrderProducer(final KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(final @RequestBody String order) {
        kafkaTemplate.send(orderTopic, order);
    }

}