package org.pastebin.application.message_db_server.services;

import lombok.RequiredArgsConstructor;
import org.pastebin.application.message_db_server.models.MessageTransactionModel;
import org.pastebin.application.message_db_server.services.minio.MinIOService;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MinIOService minIOService;

    public void save(MessageTransactionModel message) throws Exception {
        minIOService.saveToBucket(message.getMessageHash(), new ByteArrayInputStream(message.getMessage().getBytes()));
    }

    public String get(String hash) throws Exception {
        return minIOService.getFromBucket(hash);
    }

    public void delete(String hash) throws Exception {
        minIOService.deleteFromBucket(hash);
    }
}
