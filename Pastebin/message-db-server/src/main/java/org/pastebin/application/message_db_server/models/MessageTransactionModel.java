package org.pastebin.application.message_db_server.models;

import lombok.Data;

@Data
public class MessageTransactionModel {
    private String message;
    private String messageHash;

    public MessageTransactionModel(String message, String messageHash) {
        this.message = message;
        this.messageHash = messageHash;
    }

    public MessageTransactionModel() {
    }
}
