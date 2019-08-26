package com.chatapp.entity.dao.impl;


import com.chatapp.entity.Message;
import com.chatapp.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MessageDaoMockTest {

    private static MessageDaoMock messageDaoMock;
    private static User user;

    @BeforeAll
    public static void init() {
        messageDaoMock = new MessageDaoMock();
        user = new User("Olya", "email@com.com");
        messageDaoMock.addMessage(user, "Hello all");
        messageDaoMock.addMessage(user, "Glad to see you");
    }


    @Test
    public void testGetMessagesOfTheUserNatalie() {
        User userNatalie = new User("Natalie", "natalie@chatapp.com");
        messageDaoMock.addMessage(userNatalie, "Happy to see you all!");
        List<Message> messages = messageDaoMock.getAllMessagesOfUser(userNatalie);
        assertEquals(messages.get(0).getText(), "Happy to see you all!");
        List<Message> messagesOlya = messageDaoMock.getAllMessagesOfUser(user);
        assertEquals(messagesOlya.get(0).getText(), "Hello all");
    }

    @Test
    public void testGetMessagesOfTheUserInChronologicalOrder() {
        User userNatalie = new User("Natalie", "natalie@chatapp.com");
        messageDaoMock.addMessage(userNatalie, "Happy to see you all!");
        List<Message> messages = messageDaoMock.getAllMessagesOfUserInChronologicalOrder(userNatalie);
        assertEquals("Happy to see you all!", messages.get(0).getText());
        List<Message> messagesOlya = messageDaoMock.getAllMessagesOfUserInChronologicalOrder(user);
        assertEquals("Glad to see you", messagesOlya.get(0).getText());
    }

    @Test
    public void testGetMessagesByUserId() {
        List<Message> messages = messageDaoMock.getAllMessagesOfUserId(0);
        assertEquals("Hello", messages.isEmpty() ? "" : messages.get(0).getText());
    }

}
