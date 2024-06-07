package org.pastebin.application.message_db_server.services.kafka;

import lombok.RequiredArgsConstructor;
import org.pastebin.application.message_db_server.models.MessageTransactionModel;
import org.pastebin.application.message_db_server.services.MessageService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaDeleteListener {
    private final MessageService messageService;

    @KafkaListener(topics = "${kafka.topic.delete}", groupId = "${kafka.groupid}", containerFactory = "deleteListenerContainerFactory")
    public void save(String hash) {
        try {
            messageService.delete(hash);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
