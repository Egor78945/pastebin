package org.pastebin.application.message_db_server.controllers.advice;

import org.pastebin.application.message_db_server.annotations.MessageControllerExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(annotations = MessageControllerExceptionHandler.class)
public class MessageControllerAdvice {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler() {
        return ResponseEntity.ok(null);
    }
}
