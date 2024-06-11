package org.pastebin.application.db_server.services.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.pastebin.application.db_server.models.entities.MessageHash;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class MessageHashServiceAspect {

    @After("execution(public java.lang.Long save(org.pastebin.application.db_server.models.entities.MessageHash))")
    public void afterSaveMessageHashAdvice(JoinPoint joinPoint) {
        MessageHash messageHash = (MessageHash) joinPoint.getArgs()[0];
        log.info(String.format("Message Hash Service: Message with hash %s have been saved", messageHash.getHash()));
    }

    @After("execution(public org.pastebin.application.db_server.models.entities.MessageHash getById(java.lang.Long))")
    public void afterGetMessageHashByIdAdvice(JoinPoint joinPoint) {
        Long messageHashId = (Long) joinPoint.getArgs()[0];
        log.info(String.format("Message Hash Service: Message hash with id %s have been gotten", messageHashId));
    }

    @After("execution(public void deleteById(java.lang.Long))")
    public void afterDeleteMessageHashByIdAdvice(JoinPoint joinPoint) {
        Long messageHashId = (Long) joinPoint.getArgs()[0];
        log.info(String.format("Message Hash Service: Message with id %s have been deleted", messageHashId));
    }
}
