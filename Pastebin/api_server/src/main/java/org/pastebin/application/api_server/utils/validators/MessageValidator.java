package org.pastebin.application.api_server.utils.validators;

public class MessageValidator {
    public static boolean isValidMessage(String message) {
        return message.length() > 50 && message.length() < 500;
    }
}
