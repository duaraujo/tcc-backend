package com.ifam.tccbackend.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderConsumer {

    //@KafkaListener(topics = "${order.face-recognized}")
    //public void consumer(String order) {
      //  log.info("Order: " + order);
    //}

}