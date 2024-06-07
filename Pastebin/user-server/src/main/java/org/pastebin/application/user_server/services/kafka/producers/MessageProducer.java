package org.pastebin.application.user_server.services.kafka.producers;

import org.pastebin.application.user_server.models.MessageTransactionModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {
    private final KafkaTemplate<String, MessageTransactionModel> saveKafkaTemplate;
    private final KafkaTemplate<String, String> deleteKafkaTemplate;
    private final String KAFKA_SAVE_TOPIC;
    private final String KAFKA_DELETE_TOPIC;

    public MessageProducer(KafkaTemplate<String, MessageTransactionModel> saveKafkaTemplate, KafkaTemplate<String, String> deleteKafkaTemplate, @Value("${kafka.topic.save}") String KAFKA_SAVE_TOPIC, @Value("${kafka.topic.delete}") String KAFKA_DELETE_TOPIC) {
        this.saveKafkaTemplate = saveKafkaTemplate;
        this.deleteKafkaTemplate = deleteKafkaTemplate;
        this.KAFKA_SAVE_TOPIC = KAFKA_SAVE_TOPIC;
        this.KAFKA_DELETE_TOPIC = KAFKA_DELETE_TOPIC;
    }

    public void sendToSave(MessageTransactionModel message) {
        saveKafkaTemplate.send(KAFKA_SAVE_TOPIC, message);
    }

    public void sendToDelete(String messageHash) {
        deleteKafkaTemplate.send(KAFKA_DELETE_TOPIC, messageHash);
    }
}
