package org.pastebin.application.user_server.services.kafka.producers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class DBProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String KAFKA_TOPIC;

    public DBProducer(KafkaTemplate<String, String> kafkaTemplate, @Value("${kafka.topic}") String KAFKA_TOPIC) {
        this.kafkaTemplate = kafkaTemplate;
        this.KAFKA_TOPIC = KAFKA_TOPIC;
    }

    public void send(String message) {
        kafkaTemplate.send(KAFKA_TOPIC, message);
    }
}
