package org.pastebin.application.api_server.services;

import lombok.RequiredArgsConstructor;
import org.pastebin.application.api_server.exceptions.MessageFormatException;
import org.pastebin.application.api_server.exceptions.RequestCancelledException;
import org.pastebin.application.api_server.services.kafka.producers.DBProducer;
import org.pastebin.application.api_server.services.web_client.WebClientService;
import org.pastebin.application.api_server.utils.validators.MessageValidator;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final DBProducer dbProducer;
    private final WebClientService webClientService;

    public Long postMessage(String message) throws MessageFormatException {
        if (MessageValidator.isValidMessage(message)) {
            return webClientService.postRequest(String.format("http://localhost:8081/database/save?hash=%s", message.hashCode()), Long.class);
        }
        throw new MessageFormatException("Message size is wrong. Message must be bigger 50 char and less 500 chars.");
//        dbProducer.send(message);
    }

    public Integer getMessageHash(Long id) throws RequestCancelledException {
        Integer hash = webClientService.getRequest(String.format("http://db_server/database?id=%s", id), Integer.class);
        if (hash != 0) {
            return hash;
        }
        throw new RequestCancelledException(String.format("Message with id %s is not found.", id));
    }

    public String buildPersonalReference(Long id) {
        return String.format("http://localhost:8080/message?id=%s", id);
    }
}