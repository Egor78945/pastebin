package org.pastebin.application.message_db_server.service.aspect.minio;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class MinIOServiceAspect {
    @After("execution(public void createBucket())")
    public void afterCreateBucketAdvice() {
        log.info("MinIOService: Bucket have been created");
    }

    @After("execution(public void saveToBucket(java.lang.String, java.io.InputStream))")
    public void afterSaveToBucketAdvice(JoinPoint joinPoint) {
        String messageHash = (String) joinPoint.getArgs()[0];
        log.info(String.format("MinIOService: Message have been saved to bucket: %s", messageHash));
    }

    @After("execution(public java.lang.String getFromBucket(java.lang.String))")
    public void afterGetFromBucketAdvice(JoinPoint joinPoint) {
        String messageHash = (String) joinPoint.getArgs()[0];
        log.info(String.format("MinIOService: Message have been gotten from bucket: %s", messageHash));
    }

    @After("execution(public void deleteFromBucket(java.lang.String))")
    public void afterDeleteFromBucketAdvice(JoinPoint joinPoint) {
        String messageHash = (String) joinPoint.getArgs()[0];
        log.info(String.format("MinIOService: Message have been deleted from bucket: %s", messageHash));
    }
}
