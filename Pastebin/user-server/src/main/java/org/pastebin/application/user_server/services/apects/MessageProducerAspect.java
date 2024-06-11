package org.pastebin.application.user_server.services.apects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.pastebin.application.user_server.models.MessageTransactionModel;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class MessageProducerAspect {
    @After("execution(public void sendToSave(org.pastebin.application.user_server.models.MessageTransactionModel))")
    public void afterSendToSaveAdvice(JoinPoint joinPoint) {
        MessageTransactionModel messageTransactionModel = (MessageTransactionModel) joinPoint.getArgs()[0];
        log.info(String.format("Message Producer: Message have been sent to save topic: %s", messageTransactionModel.getMessage()));
    }

    @After("execution(public void sendToDelete(java.lang.String))")
    public void afterSendToDeleteAdvice(JoinPoint joinPoint) {
        String messageHash = (String) joinPoint.getArgs()[0];
        log.info(String.format("Message Producer: Message hash have been sent to delete topic: %s", messageHash));
    }
}
