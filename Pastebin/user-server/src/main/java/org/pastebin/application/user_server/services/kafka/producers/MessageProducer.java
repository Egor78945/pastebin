package org.pastebin.application.user_server.services.kafka.producers;

import org.pastebin.application.user_server.configurations.kafka.KafkaDetails;
import org.pastebin.application.user_server.models.MessageTransactionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {
    private final KafkaTemplate<String, MessageTransactionModel> saveKafkaTemplate;
    private final KafkaTemplate<String, String> deleteKafkaTemplate;
    private final KafkaDetails kafkaDetails;

    @Autowired
    public MessageProducer(KafkaTemplate<String, MessageTransactionModel> saveKafkaTemplate, KafkaTemplate<String, String> deleteKafkaTemplate, KafkaDetails kafkaDetails) {
        this.saveKafkaTemplate = saveKafkaTemplate;
        this.deleteKafkaTemplate = deleteKafkaTemplate;
        this.kafkaDetails = kafkaDetails;
    }

    public void sendToSave(MessageTransactionModel message) {
        saveKafkaTemplate.send(kafkaDetails.getKAFKA_SAVE_TOPIC(), message);
    }

    public void sendToDelete(String messageHash) {
        deleteKafkaTemplate.send(kafkaDetails.getKAFKA_DELETE_TOPIC(), messageHash);
    }
}
