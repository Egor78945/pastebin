package org.pastebin.application.user_server.services.kafka.producers;

import org.apache.hc.core5.http.Message;
import org.pastebin.application.user_server.models.MessageTransactionModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {
    private final KafkaTemplate<String, MessageTransactionModel> kafkaTemplate;
    private final String KAFKA_SAVE_TOPIC;
    private final String KAFKA_DELETE_TOPIC;

    public MessageProducer(KafkaTemplate<String, MessageTransactionModel> kafkaTemplate, @Value("${kafka.topic.save}") String KAFKA_SAVE_TOPIC, @Value("${kafka.topic.delete}") String KAFKA_DELETE_TOPIC) {
        this.kafkaTemplate = kafkaTemplate;
        this.KAFKA_SAVE_TOPIC = KAFKA_SAVE_TOPIC;
        this.KAFKA_DELETE_TOPIC = KAFKA_DELETE_TOPIC;
    }

    public void sendToSave(MessageTransactionModel message) {
        kafkaTemplate.send(KAFKA_SAVE_TOPIC, message);
    }

    public void sendToDelete(MessageTransactionModel message) {
        kafkaTemplate.send(KAFKA_DELETE_TOPIC, message);
    }
}
