package org.pastebin.application.user_server.services.apect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class MessageServiceAspect {
    @AfterReturning("execution(public Long postMessage(java.lang.String))")
    public void afterPostMessageAdvice(JoinPoint joinPoint) {
        String message = (String) joinPoint.getArgs()[0];
        log.info(String.format("Message service: Message have been saved: %s", message));
    }

    @AfterReturning("execution(public java.lang.Integer getMessageHash(java.lang.Long))")
    public void afterReturningGetMessageHashAdvice(JoinPoint joinPoint) {
        Long messageHashId = (Long) joinPoint.getArgs()[0];
        log.info(String.format("Message service: Message hash with id %s have been gotten", messageHashId));
    }

    @AfterReturning("execution(public java.lang.String getMessage(java.lang.Long))")
    public void afterReturningGetMessageAdvice(JoinPoint joinPoint) {
        Long messageHashId = (Long) joinPoint.getArgs()[0];
        log.info(String.format("Message service: Message with message hash with id %s have been gotten", messageHashId));
    }

    @AfterReturning("execution(public java.lang.String buildPersonalReference(java.lang.Long))")
    public void afterReturningBuildPersonalReferenceAdvice(JoinPoint joinPoint) {
        Long messageHashId = (Long) joinPoint.getArgs()[0];
        log.info(String.format("Message service: Personal reference for message with message hash with id %s have been build", messageHashId));
    }

    @After("execution(public java.lang.Long deleteMessage(java.lang.Long))")
    public void afterDeleteMessageAdvice(JoinPoint joinPoint) {
        Long messageHashId = (Long) joinPoint.getArgs()[0];
        log.info(String.format("Message service: Message with message hash with id %s have been sent to delete", messageHashId));
    }
}
