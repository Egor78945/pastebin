package org.pastebin.application.message_db_server.services.kafka;

import lombok.RequiredArgsConstructor;
import org.pastebin.application.message_db_server.models.MessageTransactionModel;
import org.pastebin.application.message_db_server.services.MessageService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaSaveListener {
    private final MessageService messageService;

    @KafkaListener(topics = "${kafka.topic.save}", groupId = "${kafka.groupid}", containerFactory = "saveListenerContainerFactory")
    public void listenToSave(MessageTransactionModel message) {
        try {
            messageService.save(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
