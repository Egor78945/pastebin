package org.pastebin.application.user_server.models;

import lombok.Data;

@Data
public class MessageTransactionModel {
    private String message;
    private int messageHash;

    public MessageTransactionModel(String message, int messageHash) {
        this.message = message;
        this.messageHash = messageHash;
    }

    public MessageTransactionModel() {
    }
}
