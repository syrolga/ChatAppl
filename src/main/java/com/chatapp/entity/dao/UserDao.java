package com.chatapp.entity.dao;

import com.chatapp.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public interface UserDao {

    void follow(User user, User following);

    void followById(int id, int followingId);

    void unfollow(User user, User following);

    void addUser(User user);

    void addUser(String nickname, String email);

    void unfollowAll(User user);

    Set<User> getFollowers(User user);

    Set<User> getFollowings(User user);

    List<User> getAllUsers();

    Optional<User> getUserByNickname(String nickname);

    Optional<User> getUserById(int id);

    void addMessage(User user, String message);

    void addMessageByUserNickName(String nickname, String message);

    void deleteUser(int id);


}
