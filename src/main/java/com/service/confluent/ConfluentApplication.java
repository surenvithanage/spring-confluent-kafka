package com.service.confluent;

import com.service.confluent.avro.UserEvent;
import com.service.confluent.service.UserEventProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ConfluentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfluentApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(UserEventProducer producer) {
        return args -> {
            for (int i = 1; i <= 5; i++) {
                UserEvent event = UserEvent.newBuilder()
                        .setUserId("user-" + i)
                        .setEventType("event-type-" + (i % 3))
                        .setTimestamp(System.currentTimeMillis())
                        .build();

                producer.send(event);
                System.out.println("Sent event: " + event);
            }
        };
    }
}
