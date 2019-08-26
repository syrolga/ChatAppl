package com.chatapp.entity.dao.impl;

import com.chatapp.exception.AppChatException;

public class InputValidator {

    public static void validateObject(Object obj) {
        if (obj == null) {
            throw new AppChatException("Input Object can't be null");
        }

        if (obj instanceof String) {
            if (obj == "") {
                throw new AppChatException("Input value can't be null");
            }
        }
    }

    public static void validateMessage(String message) {
        if (message.length() > 130) {
            throw new AppChatException("Message is too long");
        }
        if (message.equals("")) {
            throw new AppChatException("Message can't be empty");
        }
        if (message == null) {
            throw new AppChatException("Message can't be null");
        }
    }


}
