package org.pastebin.application.db_server.exceptions;

public class RequestCancelledException extends Exception{
    public RequestCancelledException(String message){
        super(message);
    }
}
