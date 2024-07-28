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
public class RedisServiceAspect {
    @After("execution(public void save(java.lang.String, java.lang.String, java.lang.Object))")
    public void afterSaveAdvice(JoinPoint joinPoint) {
        final String HASH_KEY = (String) joinPoint.getArgs()[0];
        final String KEY = (String) joinPoint.getArgs()[1];
        Object value = joinPoint.getArgs()[2];
        log.info(String.format("Redis(HASH_KEY=%s,KEY=%s): Object saved in redis: %s", HASH_KEY, KEY, value));
    }

    @AfterReturning("execution(public java.lang.Object get(java.lang.String, java.lang.String))")
    public void afterReturningGetAdvice(JoinPoint joinPoint) {
        final String HASH_KEY = (String) joinPoint.getArgs()[0];
        final String KEY = (String) joinPoint.getArgs()[1];
        log.info(String.format("Redis(HASH_KEY=%s,KEY=%s): Object has been gotten", HASH_KEY, KEY));
    }

    @After("execution(public void delete(java.lang.String, java.lang.String))")
    public void afterDeleteAdvice(JoinPoint joinPoint) {
        final String HASH_KEY = (String) joinPoint.getArgs()[0];
        final String KEY = (String) joinPoint.getArgs()[1];
        log.info(String.format("Redis(HASH_KEY=%s,KEY=%s): Object has been deleted", HASH_KEY, KEY));
    }
}
