package org.pastebin.application.message_db_server.service.aspect.kafka.listener;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.pastebin.application.message_db_server.model.MessageTransactionModel;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class KafkaSaveListenerAspect {
    @After("execution(public void listenToSave(org.pastebin.application.message_db_server.model.MessageTransactionModel))")
    public void listenToSaveAdvice(JoinPoint joinPoint) {
        MessageTransactionModel messageTransactionModel = (MessageTransactionModel) joinPoint.getArgs()[0];
        log.info(String.format("Kafka Save Listener: Message have been sent to save: %s", messageTransactionModel.getMessage()));
    }
}
