package org.pastebin.application.user_server.exceptions;

public class RequestCancelledException extends Exception {
    public RequestCancelledException(String message) {
        super(message);
    }
}
