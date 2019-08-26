package com.chatapp.exception;

public class AppChatException extends RuntimeException {

    public AppChatException(String message) {
        super(message);
    }

    public AppChatException(String message, Throwable cause) {
        super(message, cause);
    }
}
