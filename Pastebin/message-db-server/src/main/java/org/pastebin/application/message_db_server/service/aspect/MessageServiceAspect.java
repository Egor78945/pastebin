package org.pastebin.application.message_db_server.service.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.pastebin.application.message_db_server.model.MessageTransactionModel;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class MessageServiceAspect {
    @After("execution(public void save(org.pastebin.application.message_db_server.model.MessageTransactionModel))")
    public void afterSaveAdvice(JoinPoint joinPoint) {
        MessageTransactionModel messageTransactionModel = (MessageTransactionModel) joinPoint.getArgs()[0];
        log.info(String.format("Message Service: Message have been sent to save: %s", messageTransactionModel.getMessage()));
    }

    @After("execution(public java.lang.String get(java.lang.String))")
    public void afterGetAdvice(JoinPoint joinPoint) {
        String messageHash = (String) joinPoint.getArgs()[0];
        log.info(String.format("Message Service: Message hash have been sent to get: %s", messageHash));
    }

    @After("execution(public void delete(java.lang.String))")
    public void afterDeleteAdvice(JoinPoint joinPoint) {
        String messageHash = (String) joinPoint.getArgs()[0];
        log.info(String.format("Message Service: Message hash have been sent to delete: %s", messageHash));
    }
}
