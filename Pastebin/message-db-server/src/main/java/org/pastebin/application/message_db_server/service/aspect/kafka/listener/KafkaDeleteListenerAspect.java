package org.pastebin.application.message_db_server.service.aspect.kafka.listener;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class KafkaDeleteListenerAspect {
    @After("execution(public void listenToDelete(java.lang.String))")
    public void listenToDeleteAdvice(JoinPoint joinPoint) {
        String messageHash = (String) joinPoint.getArgs()[0];
        log.info(String.format("Kafka Delete Listener: Message hash have been sent to delete: %s", messageHash));
    }
}
