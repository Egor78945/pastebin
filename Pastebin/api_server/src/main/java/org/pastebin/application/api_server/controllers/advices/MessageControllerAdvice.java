package org.pastebin.application.api_server.controllers.advices;

import org.pastebin.application.api_server.annotations.MessageControllerExceptionHandler;
import org.pastebin.application.api_server.exceptions.MessageFormatException;
import org.pastebin.application.api_server.exceptions.RequestCancelledException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(annotations = MessageControllerExceptionHandler.class)
public class MessageControllerAdvice {
    @ExceptionHandler(MessageFormatException.class)
    public ResponseEntity<String> messageFormatExceptionHandler(MessageFormatException e) {
        return ResponseEntity.ok(e.getMessage());
    }

    @ExceptionHandler(RequestCancelledException.class)
    public ResponseEntity<String> requestCancelledExceptionHandler(RequestCancelledException e) {
        return ResponseEntity.ok(e.getMessage());
    }
}