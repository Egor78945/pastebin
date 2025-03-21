package org.pastebin.application.user_server.services;

import lombok.RequiredArgsConstructor;
import org.pastebin.application.user_server.exception.MessageFormatException;
import org.pastebin.application.user_server.exception.RequestCancelledException;
import org.pastebin.application.user_server.model.MessageTransactionModel;
import org.pastebin.application.user_server.services.kafka.producer.MessageProducer;
import org.pastebin.application.user_server.services.redis.RedisService;
import org.pastebin.application.user_server.services.web_client.WebClientService;
import org.pastebin.application.user_server.util.validator.MessageValidator;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageProducer messageProducer;
    private final WebClientService webClientService;
    private final RedisService redisService;
    private final String HASH_KEY = "message";

    public Long postMessage(String message) throws MessageFormatException {
        if (MessageValidator.isValidMessage(message)) {
            Long hashId = webClientService.postRequest(String.format("http://db-server/database/save?hash=%s", message.hashCode()), Long.class);
            messageProducer.sendToSave(new MessageTransactionModel(message, String.valueOf(message.hashCode())));
            return hashId;
        }
        throw new MessageFormatException("Message size is wrong. Message must be bigger 50 char and less 500 chars.");
    }

    public Integer getMessageHash(Long id) throws RequestCancelledException {
        Integer hash = webClientService.getRequest(String.format("http://db-server/database?id=%s", id), Integer.class);
        if (hash == 0) {
            throw new RequestCancelledException(String.format("Message with id %s is not found.", id));
        }
        return hash;
    }

    public String getMessage(Long id) throws RequestCancelledException {
        String message = (String) redisService.get(HASH_KEY, id.toString());
        if (message == null) {
            Integer hash = getMessageHash(id);
            message = webClientService.getRequest(String.format("http://message-db-server/minio?hash=%s", hash), String.class);
            if (message == null) {
                throw new RequestCancelledException(String.format("Message with id %s is not found.", id));
            }
            redisService.save(HASH_KEY, id.toString(), message);
        }
        return message;
    }

    public String buildPersonalReference(Long id) {
        return String.format("http://localhost:8080/message?id=%s", id);
    }

    public Long deleteMessage(Long id) throws RequestCancelledException {
        Integer hash = webClientService.getRequest(String.format("http://db-server/database?id=%s", id), Integer.class);
        if (hash != 0) {
            if (redisService.get(HASH_KEY, id.toString()) != null) {
                redisService.delete(HASH_KEY, id.toString());
            }
            messageProducer.sendToDelete(String.valueOf(hash));
            return webClientService.deleteRequest(String.format("http://db-server/database/delete?id=%s", id), Long.class);
        }
        throw new RequestCancelledException(String.format("Message with id %s is not found.", id));
    }
}