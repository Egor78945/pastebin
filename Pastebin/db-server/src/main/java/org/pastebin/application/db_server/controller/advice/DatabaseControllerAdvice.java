package org.pastebin.application.db_server.controller.advice;

import lombok.extern.slf4j.Slf4j;
import org.pastebin.application.db_server.annotation.DatabaseControllerExceptionHandler;
import org.pastebin.application.db_server.exception.RequestCancelledException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(annotations = DatabaseControllerExceptionHandler.class)
@Slf4j
public class DatabaseControllerAdvice {
    @ExceptionHandler(RequestCancelledException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Integer> requestCancelledExceptionHandler(Exception e) {
        log.error(String.format("Database Controller: %s", e.getMessage()));
        return new ResponseEntity<>(0, HttpStatus.OK);
    }
}