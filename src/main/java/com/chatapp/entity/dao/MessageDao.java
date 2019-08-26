package com.chatapp.entity.dao;

import com.chatapp.entity.Message;
import com.chatapp.entity.User;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface MessageDao {

    void addMessage(User user, String message);


    void removeMessage(Message message);

    Optional<Message> getMessageByDate(Instant instant);

    List<Message> getAllMessagesOfUser(User user);

    List<Message> getAllMessagesOfUserInChronologicalOrder(User user);

    Optional<Message> getMessageById(int userId);

    List<Message> getAllMessages();

    List<Message> getAllMessagesOfUserId(int userId);

    List<Message> getAllMessagesOfUserIdInChronologicalOrder(int userId);

    List<Message> getAllMessagesOfUsersInChronologicalOrder(Set<User> users);

}
