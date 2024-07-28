package org.pastebin.application.message_db_server.controller.advice;

import lombok.extern.slf4j.Slf4j;
import org.pastebin.application.message_db_server.annotation.MessageControllerExceptionHandler;
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
