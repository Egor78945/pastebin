package org.pastebin.application.user_server.controller.advice;

import lombok.extern.slf4j.Slf4j;
import org.pastebin.application.user_server.annotation.MessageControllerExceptionHandler;
import org.pastebin.application.user_server.exception.MessageFormatException;
import org.pastebin.application.user_server.exception.RequestCancelledException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(annotations = MessageControllerExceptionHandler.class)
@Slf4j
public class MessageControllerAdvice {
    @ExceptionHandler(MessageFormatException.class)
    public ResponseEntity<String> messageFormatExceptionHandler(MessageFormatException e) {
        log.error(String.format("Message Controller: %s", e.getMessage()));
        return ResponseEntity.ok(e.getMessage());
    }

    @ExceptionHandler(RequestCancelledException.class)
    public ResponseEntity<String> requestCancelledExceptionHandler(RequestCancelledException e) {
        log.error(String.format("Message Controller: %s", e.getMessage()));
        return ResponseEntity.ok(e.getMessage());
    }
}
