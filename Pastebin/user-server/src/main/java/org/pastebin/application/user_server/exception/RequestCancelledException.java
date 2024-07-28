package org.pastebin.application.user_server.exception;

public class RequestCancelledException extends Exception {
    public RequestCancelledException(String message) {
        super(message);
    }
}
