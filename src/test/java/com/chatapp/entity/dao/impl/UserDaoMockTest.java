package com.chatapp.entity.dao.impl;

import java.util.List;
import java.util.Set;

import com.chatapp.entity.User;
import com.chatapp.entity.dao.UserDao;
import com.chatapp.exception.AppChatException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserDaoMockTest {

    private static UserDao userDao;

    @BeforeAll
    public static void init() {
        userDao = new UserDaoMock();
        userDao.addUser(new User("Olga", "olya@chatapp.com"));
        userDao.addUser(new User("Natalie", "olya@chatapp.com"));

    }

    @Test
    public void testUserAdding() {
        User newUser = new User("newUser", "newUser@chatapp.com");
        userDao.addUser(newUser);
        assertEquals(newUser, userDao.getUserByNickname("newUser").get());
    }

    @Test
    public void testExceptionWhenNullUserAdding() {
        Assertions.assertThrows(AppChatException.class, () -> {
            userDao.addUser(null);
        });
    }

    @Test
    public void testExceptionWhenGetUserByEmptyNickName() {
        Assertions.assertThrows(AppChatException.class, () -> {
            userDao.getUserByNickname("");
        });
    }

    @Test
    public void testAddingMessage() {
        User user = new User("Alex", "alex@chatapp.com");
        userDao.addUser(user);
        String message = "Hello";
        assertEquals(0, user.getMessages().size());
        userDao.addMessage(user, message);
        assertEquals(1, user.getMessages().size());
    }


    @Test
    public void testOlgaFollowsNatalie() {
        User olga = userDao.getUserByNickname("Olga").get();
        User natalie = userDao.getUserByNickname("Natalie").get();
        userDao.follow(olga, natalie);
        userDao.getFollowings(olga).contains(natalie);
        assertTrue(userDao.getFollowings(olga).contains(natalie));
    }

    @Test
    public void testOlgaIsFollowerOfNatalie() {
        User olga = userDao.getUserByNickname("Olga").get();
        User natalie = userDao.getUserByNickname("Natalie").get();
        userDao.follow(olga, natalie);
        Set<User> followers = userDao.getFollowers(natalie);
        assertTrue(followers.contains(olga));
    }

    @Test
    public void testOlgaUnfollowsNatalie() {
        User olga = userDao.getUserByNickname("Olga").get();
        User natalie = userDao.getUserByNickname("Natalie").get();
        userDao.follow(olga, natalie);
        userDao.getFollowings(olga).contains(natalie);
        assertTrue(userDao.getFollowings(olga).contains(natalie));
        userDao.unfollow(olga, natalie);
        assertTrue(!userDao.getFollowings(olga).contains(natalie));
    }

    @Test
    public void testGetAllUsers() {
        List<User> users = userDao.getAllUsers();
        System.out.println(users);
        assertEquals(6, users.size());
    }

    @Test
    public void testOlgaUnfollowingAll() {
        User olga = userDao.getUserByNickname("Olga").get();
        User natalie = userDao.getUserByNickname("Natalie").get();
        User userUserovich = userDao.getUserByNickname("UserUserovich").get();
        userDao.follow(olga, natalie);
        userDao.follow(olga, userUserovich);
        assertEquals(2, userDao.getFollowings(olga).size());
        userDao.unfollowAll(olga);
        assertEquals(0, userDao.getFollowings(olga).size());
    }

}
