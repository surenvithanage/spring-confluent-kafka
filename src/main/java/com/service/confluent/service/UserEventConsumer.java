package com.service.confluent.service;

import com.service.confluent.avro.UserEvent;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserEventConsumer {

    @KafkaListener(topics = "#{'${topic.name}'}", groupId = "avro-demo-group", containerFactory = "kafkaListenerContainerFactory")
    public void listen(ConsumerRecord<String, UserEvent> record) {
        System.out.println("Payload: " + record);
        System.out.println("Received UserEvent: " + record.value());
    }
}

