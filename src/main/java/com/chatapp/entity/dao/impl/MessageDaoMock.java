package com.chatapp.entity.dao.impl;

import com.chatapp.entity.Message;
import com.chatapp.entity.User;
import com.chatapp.entity.dao.MessageDao;
import com.chatapp.exception.AppChatException;
import com.chatapp.storage.Data;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class MessageDaoMock implements MessageDao {


    private List<Message> messages = new ArrayList(10);

    public MessageDaoMock() {
        messages = Data.inputDataMessages();

    }

    public List<Message> getAllMessages() {
        return messages;
    }

    @Override
    public Optional<Message> getMessageById(int id) {
        try {
            Message message = messages.get(id);
            return Optional.ofNullable(message);
        } catch (IndexOutOfBoundsException ex) {
            throw new AppChatException("Message max is: " + messages.size() + ex);
        }

    }


    @Override
    public void addMessage(User user, String message) {
        messages.add(new Message(user.getId(), message));
    }

    @Override
    public void removeMessage(Message message) {
        messages.remove(message);
    }

    @Override
    public Optional<Message> getMessageByDate(Instant instant) {
        Optional<Message> messageByDate = messages.stream().filter(message -> message.getCreationDate().compareTo(instant) == 0).findFirst();

        return messageByDate;
    }


    @Override
    public List<Message> getAllMessagesOfUser(User user) {
        List<Message> messagesOfUser = messages.stream()
                .filter(message -> message.getCreatorId() == user.getId())
                .collect(Collectors.toList());
        return messagesOfUser;
    }

    @Override
    public List<Message> getAllMessagesOfUserId(int userId) {
        List<Message> messagesOfUser = messages.stream()
                .filter(message -> message.getCreatorId() == userId)
                .collect(Collectors.toList());
        return messagesOfUser;
    }

    @Override
    public List<Message> getAllMessagesOfUserInChronologicalOrder(User user) {

        return getAllMessagesOfUserIdInChronologicalOrder(user.getId());
    }

    @Override
    public List<Message> getAllMessagesOfUserIdInChronologicalOrder(int userId) {

        List<Message> messagesOfUser = getAllMessagesOfUserId(userId);
        TreeMap<Instant, Message> messageChronologicalTree = new TreeMap<>();
        for (Message message : messagesOfUser) {
            messageChronologicalTree.put(message.getCreationDate(), message);
        }
        List<Message> resultList = messageChronologicalTree.values().stream()
                .collect(Collectors.toList());
        return resultList;
    }


    @Override
    public List<Message> getAllMessagesOfUsersInChronologicalOrder(Set<User> users) {
        TreeMap<Instant, Message> messageChronologicalTree = new TreeMap<>();
        for (User u : users) {
            List<Message> messagesOfUser = getAllMessagesOfUserId(u.getId());
            for (Message message : messagesOfUser) {
                messageChronologicalTree.put(message.getCreationDate(), message);
            }
        }
        List<Message> resultList = messageChronologicalTree.values().stream()
                .collect(Collectors.toList());
        return resultList;
    }

}
