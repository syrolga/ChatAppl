package com.chatapp.controller;

import com.chatapp.entity.Message;
import com.chatapp.entity.User;
import com.chatapp.entity.dao.MessageDao;
import com.chatapp.entity.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;
import java.util.Set;

@RestController
@EnableWebMvc
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private MessageDao messageDao;

    // URL:
    // http://localhost:8080/chatapplication/timeline/{id}
    @RequestMapping(value = "/timeline/{id}",
            method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public List<Message> getTimeline(@PathVariable int id) {
        Set<User> users = userDao.getFollowers(userDao.getUserById(id).get());
        return messageDao.getAllMessagesOfUsersInChronologicalOrder(users);
    }

    // URL:
    // http://localhost:8080/chatapplication/wall/{id}
    @RequestMapping(value = "/wall/{id}",
            method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public List<Message> getWall(@PathVariable int id) {
        return messageDao.getAllMessagesOfUserIdInChronologicalOrder(id);
    }

    // URL:
    // http://localhost:8080/chatapplication/{id}/message
    @RequestMapping(value = "{id}/message",
            method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public String postMessage(@PathVariable int id, @RequestBody String message) {

        messageDao.addMessage(userDao.getUserById(id).get(), message);
        return messageDao.getAllMessagesOfUserId(id).toString();
    }

    // URL:
    // http://localhost:8080/chatapplication/users

    @RequestMapping(value = "/users", //
            method = RequestMethod.GET, //
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public List<User> getUsers() {
        List<User> list = userDao.getAllUsers();
        return list;
    }

    // URL:
    // http://localhost:8080/chatapplication/user/{id}

    @RequestMapping(value = "/user/{id}", //
            method = RequestMethod.GET, //
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public User getUser(@PathVariable("id") String id) {
        return userDao.getUserByNickname(id).get();
    }

    // URL:
    // http://localhost:8080/chatapplication/user

    @RequestMapping(value = "/user", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public User addUser(@RequestBody User user) {

        userDao.addUser(user);
        return userDao.getUserByNickname(user.getNickname()).get();

    }

    // URL:
    // http://localhost:8080/chatapplication/user/{id}/{followingId}

    @RequestMapping(value = "/user/{id}/{followingId}", //
            method = RequestMethod.PUT, //
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public User updateUser(@PathVariable int id, @PathVariable int followingId) {
        userDao.followById(id, followingId);
        return userDao.getUserById(id).get();
    }

    // URL:
    // http://localhost:8080/chatapplication/user/{id}
    @RequestMapping(value = "/user/{id}", //
            method = RequestMethod.DELETE, //
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public void deleteUser(@PathVariable("id") int id) {
        userDao.deleteUser(id);

    }
}
