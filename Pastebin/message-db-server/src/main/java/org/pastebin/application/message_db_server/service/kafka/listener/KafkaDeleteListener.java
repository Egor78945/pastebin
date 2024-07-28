package org.pastebin.application.message_db_server.service.kafka.listener;

import lombok.RequiredArgsConstructor;
import org.pastebin.application.message_db_server.service.MessageService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaDeleteListener {
    private final MessageService messageService;

    @KafkaListener(topics = "${kafka.topic.delete}", groupId = "${kafka.groupid}", containerFactory = "deleteListenerContainerFactory")
    public void listenToDelete(String hash) {
        try {
            messageService.delete(hash);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
