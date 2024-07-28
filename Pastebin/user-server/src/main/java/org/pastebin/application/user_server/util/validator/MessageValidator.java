package org.pastebin.application.user_server.util.validator;

public class MessageValidator {
    public static boolean isValidMessage(String message) {
        return message.length() > 50 && message.length() < 500;
    }
}
