package org.pastebin.application.message_db_server.controllers.advice;

import lombok.extern.slf4j.Slf4j;
import org.pastebin.application.message_db_server.annotations.MessageControllerExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(annotations = MessageControllerExceptionHandler.class)
@Slf4j
public class MessageControllerAdvice {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception e) {
        log.error(String.format("Message controller: %s", e.getMessage()));
        return ResponseEntity.ok(null);
    }
}
