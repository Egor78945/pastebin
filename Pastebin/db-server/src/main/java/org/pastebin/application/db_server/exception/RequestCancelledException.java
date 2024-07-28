package org.pastebin.application.db_server.exception;

public class RequestCancelledException extends Exception{
    public RequestCancelledException(String message){
        super(message);
    }
}
