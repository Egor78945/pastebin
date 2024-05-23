package org.pastebin.application.db_server.controllers.controller_advices;

import org.pastebin.application.db_server.annotations.DatabaseControllerExceptionHandler;
import org.pastebin.application.db_server.exceptions.RequestCancelledException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(annotations = DatabaseControllerExceptionHandler.class)
public class DatabaseControllerAdvice {
    @ExceptionHandler(RequestCancelledException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> requestCancelledExceptionHandler() {
        return new ResponseEntity<>("404", HttpStatus.NOT_FOUND);
    }
}