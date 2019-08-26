package com.chatapp.entity.dao.impl;

import com.chatapp.entity.Message;
import com.chatapp.entity.User;
import com.chatapp.entity.dao.UserDao;
import com.chatapp.storage.Data;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserDaoMock implements UserDao {
    List<User> users = new ArrayList<>(10);

    public UserDaoMock() {
        users = Data.inputDataUsers();
    }

    @Override
    public void follow(User user, User following) {
        InputValidator.validateObject(user);
        InputValidator.validateObject(following);
        Set<User> followings = user.getFollowings();
        followings.add(following);
        user.setFollowings(followings);
    }

    @Override
    public void followById(int id, int followingId) {
        follow(getUserById(id).get(), getUserById(followingId).get());
    }

    @Override
    public void unfollow(User user, User following) {
        InputValidator.validateObject(user);
        InputValidator.validateObject(following);
        Set<User> followings = user.getFollowings();
        for (User u :
                followings) {
            if (u.equals(following)) {
                followings.remove(u);
            }
        }

        user.setFollowings(followings);
    }

    @Override
    public void addUser(User user) {
        InputValidator.validateObject(user);
        users.add(user);
    }

    @Override
    public void addUser(String nickname, String email) {
        InputValidator.validateObject(nickname);
        User user = new User(nickname, email);
        users.add(user);
    }

    @Override
    public void unfollowAll(User user) {
        InputValidator.validateObject(user);
        user.setFollowings(new HashSet<>());
    }

    @Override
    public Set<User> getFollowers(User user) {
        InputValidator.validateObject(user);
        Set<User> followers = new HashSet<>();
        for (User u : users) {
            if (u.getFollowings().contains(user)) {
                followers.add(u);
            }
        }
        return followers;
    }


    @Override
    public Set<User> getFollowings(User user) {
        InputValidator.validateObject(user);
        return user.getFollowings();
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public Optional<User> getUserByNickname(String nickname) {
        InputValidator.validateObject(nickname);
        Optional<User> result = users.stream()
                .filter(x -> x.getNickname().equals(nickname)).findFirst();
        return result;
    }

    @Override
    public Optional<User> getUserById(int id) {
        return users.stream().filter(u -> u.getId() == id).findFirst();
    }

    @Override
    public void addMessage(User user, String message) {
        InputValidator.validateObject(user);
        InputValidator.validateObject(message);
        user.addMessage(new Message(user.getId(), message));
    }


    @Override
    public void addMessageByUserNickName(String nickname, String message) {
        InputValidator.validateObject(nickname);
        InputValidator.validateObject(message);
        Optional<User> user = getUserByNickname(nickname);
        user.ifPresent(user1 -> user1.addMessage(new Message(user1.getId(), message)));

    }

    @Override
    public void deleteUser(int id) {
        users.remove(getUserById(id).get());

    }

}
