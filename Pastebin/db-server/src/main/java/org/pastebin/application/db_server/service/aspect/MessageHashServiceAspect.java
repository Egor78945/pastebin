package org.pastebin.application.db_server.service.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.pastebin.application.db_server.model.entity.MessageHash;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class MessageHashServiceAspect {

    @After("execution(public java.lang.Long save(org.pastebin.application.db_server.model.entity.MessageHash))")
    public void afterSaveMessageHashAdvice(JoinPoint joinPoint) {
        MessageHash messageHash = (MessageHash) joinPoint.getArgs()[0];
        log.info(String.format("Message Hash Service: Message with hash %s have been saved", messageHash.getHash()));
    }

    @AfterReturning("execution(public org.pastebin.application.db_server.model.entity.MessageHash getById(java.lang.Long))")
    public void afterReturningGetMessageHashByIdAdvice(JoinPoint joinPoint) {
        Long messageHashId = (Long) joinPoint.getArgs()[0];
        log.info(String.format("Message Hash Service: Message hash with id %s have been gotten", messageHashId));
    }

    @After("execution(public void deleteById(java.lang.Long))")
    public void afterDeleteMessageHashByIdAdvice(JoinPoint joinPoint) {
        Long messageHashId = (Long) joinPoint.getArgs()[0];
        log.info(String.format("Message Hash Service: Message with id %s have been deleted", messageHashId));
    }
}
