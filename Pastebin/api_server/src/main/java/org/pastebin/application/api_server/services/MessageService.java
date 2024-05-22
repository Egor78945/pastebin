package org.pastebin.application.api_server.services;

import lombok.RequiredArgsConstructor;
import org.pastebin.application.api_server.exceptions.MessageFormatException;
import org.pastebin.application.api_server.services.kafka.producers.DBProducer;
import org.pastebin.application.api_server.utils.validators.MessageValidator;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final DBProducer dbProducer;

    public void sendMessage(String message) throws MessageFormatException {
        if (!MessageValidator.isValidMessage(message)) {
            throw new MessageFormatException("Message size is wrong. Message must be bigger 50 char and less 500 chars.");
        }
        dbProducer.send(message);
    }
}