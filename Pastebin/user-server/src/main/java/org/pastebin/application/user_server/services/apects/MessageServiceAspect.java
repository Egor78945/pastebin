package org.pastebin.application.user_server.services.apects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class MessageServiceAspect {
    @After("execution(public Long postMessage(java.lang.String))")
    public void afterPostMessageAdvice(JoinPoint joinPoint) {
        String message = (String) joinPoint.getArgs()[0];
        log.info(String.format("Message has been saved: %s", message));
    }

    @After("execution(public java.lang.Integer getMessageHash(java.lang.Long))")
    public void afterGetMessageHashAdvice(JoinPoint joinPoint) {
        Long messageHashId = (Long) joinPoint.getArgs()[0];
        log.info(String.format("Message hash with id %s has been gotten", messageHashId));
    }

    @After("execution(public java.lang.String getMessage(java.lang.Long))")
    public void afterGetMessageAdvice(JoinPoint joinPoint) {
        Long messageHashId = (Long) joinPoint.getArgs()[0];
        log.info(String.format("Message with message hash with id %s has been gotten", messageHashId));
    }

    @After("execution(public java.lang.String buildPersonalReference(java.lang.Long))")
    public void afterBuildPersonalReferenceAdvice(JoinPoint joinPoint) {
        Long messageHashId = (Long) joinPoint.getArgs()[0];
        log.info(String.format("Personal reference for message with message hash with id %s has been build", messageHashId));
    }

    @After("execution(public java.lang.Long deleteMessage(java.lang.Long))")
    public void afterDeleteMessageAdvice(JoinPoint joinPoint) {
        Long messageHashId = (Long) joinPoint.getArgs()[0];
        log.info(String.format("Message with message hash with id %s has been deleted", messageHashId));
    }
}
