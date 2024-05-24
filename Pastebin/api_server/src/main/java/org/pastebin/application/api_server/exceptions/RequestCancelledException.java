package org.pastebin.application.api_server.exceptions;

public class RequestCancelledException extends Exception {
    public RequestCancelledException(String message) {
        super(message);
    }
}
