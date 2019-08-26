package com.chatapp.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.chatapp.entity.Message;
import com.chatapp.entity.User;

public class Data {

    private static List<User> users = new ArrayList<>(10);
    private static List<Message> messages = new ArrayList<>(10);

    static {
        users.add(new User("UserUserovich", "user@chatapp.com"));
        users.add(new User("OlgaSyrova", "olga.syrova@chatapp.com"));
        users.add(new User("NewUser1", "NewUser1@chatapp.com"));
        users.add(new User("NewUser", "new.user@chatapp.com"));
        Integer userId = users.iterator().next().getId();
        messages.add(new Message(userId, "Hello"));
        messages.add(new Message(userId, "I'm happy to be here"));
        messages.add(new Message(userId, "Good weather today"));
        messages.add(new Message(userId, "See you all later"));
    }

    public static List<User> inputDataUsers() {
        return users;
    }

    public static List<Message> inputDataMessages() {
        return messages;
    }
}
