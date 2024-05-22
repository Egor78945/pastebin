package org.pastebin.application.api_server.exceptions;

public class MessageFormatException extends Exception {
    public MessageFormatException(String message) {
        super(message);
    }
}
