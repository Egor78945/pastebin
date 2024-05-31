package org.pastebin.application.api_server.services;

import lombok.RequiredArgsConstructor;
import org.pastebin.application.api_server.exceptions.MessageFormatException;
import org.pastebin.application.api_server.exceptions.RequestCancelledException;
import org.pastebin.application.api_server.services.web_client.WebClientService;
import org.pastebin.application.api_server.utils.validators.MessageValidator;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {
    //    private final DBProducer dbProducer;
    private final WebClientService webClientService;
    private final RedisTemplate<String, Object> redisTemplate;
    private final String HASH_KEY = "message";

    public Long postMessage(String message) throws MessageFormatException {
        if (MessageValidator.isValidMessage(message)) {
            return webClientService.postRequest(String.format("http://db-server/database/save?hash=%s", message.hashCode()), Long.class);
        }
        throw new MessageFormatException("Message size is wrong. Message must be bigger 50 char and less 500 chars.");
//        dbProducer.send(message);
    }

    public Integer getMessageHash(Long id) throws RequestCancelledException {
        Integer hash = (Integer) redisTemplate.opsForHash().get(HASH_KEY, id.toString());
        if (hash == null) {
            hash = webClientService.getRequest(String.format("http://db-server/database?id=%s", id), Integer.class);
            if (hash == 0) {
                throw new RequestCancelledException(String.format("Message with id %s is not found.", id));
            }
            redisTemplate.opsForHash().put(HASH_KEY, id.toString(), hash);
        }
        return hash;
    }

    public String buildPersonalReference(Long id) {
        return String.format("http://localhost:8080/message?id=%s", id);
    }

    public Long deleteMessage(Long id) throws RequestCancelledException {
        Integer check = webClientService.getRequest(String.format("http://db-server/database?id=%s", id), Integer.class);
        if (check != 0) {
            if(redisTemplate.opsForHash().get(HASH_KEY, id.toString()) != null){
                redisTemplate.opsForHash().delete(HASH_KEY, id.toString());
            }
            return webClientService.deleteRequest(String.format("http://db-server/database/delete?id=%s", id), Long.class);
        }
        throw new RequestCancelledException(String.format("Message with id %s is not found.", id));
    }
}