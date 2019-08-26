package com.chatapp.app;

import com.chatapp.entity.User;
import com.chatapp.entity.dao.MessageDao;
import com.chatapp.entity.dao.UserDao;
import com.chatapp.entity.dao.impl.MessageDaoMock;
import com.chatapp.entity.dao.impl.UserDaoMock;

public class Demo {
    public static void main(String[] args) {
        MessageDao messageDao = new MessageDaoMock();
        UserDao userDao = new UserDaoMock();

        System.out.println("All available users:");
        System.out.println(userDao.getAllUsers());

        System.out.println("Adding User John");
        User john = new User("John", "john@chatapp.com");
        userDao.addUser(john);
        System.out.println("Adding User Alex");
        User alex = new User("Alex", "alex@chatapp.com");
        userDao.addUser(alex);
        System.out.println("Adding User Olga");
        User olga = new User("Olga", "olga@chatapp.com");
        userDao.addUser(olga);

        System.out.println("User John start to follow user Olga:");
        userDao.follow(john,olga);

        System.out.println("User John start to follow user Alex:");
        userDao.follow(john,alex);

        System.out.println("User Olga start to follow user Alex:");
        userDao.follow(olga,alex);

        System.out.println("Count of followers of Olga:");
        System.out.println(userDao.getFollowers(olga).size());

        System.out.println("Followers of Olga:");
        System.out.println(userDao.getFollowers(olga));

        System.out.println("Count of followings of Olga:");
        System.out.println(userDao.getFollowings(olga).size());

        System.out.println("Followings of Olga:");
        System.out.println(userDao.getFollowings(olga));

        System.out.println("Count of followers of Alex:");
        System.out.println(userDao.getFollowers(alex).size());

        System.out.println("Followers of Alex:");
        System.out.println(userDao.getFollowers(alex));

        System.out.println("Count of followings of Alex:");
        System.out.println(userDao.getFollowings(alex).size());

        System.out.println("Followings of Alex:");
        System.out.println(userDao.getFollowings(alex));

        System.out.println("Count of followers of John:");
        System.out.println(userDao.getFollowers(john).size());

        System.out.println("Followers of John:");
        System.out.println(userDao.getFollowers(john));

        System.out.println("Count of followings of John:");
        System.out.println(userDao.getFollowings(john).size());

        System.out.println("Followings of John:");
        System.out.println(userDao.getFollowings(john));

        System.out.println("Olga added Message:");
        messageDao.addMessage(olga,"Hello Every One!");
        System.out.println(messageDao.getAllMessagesOfUserId(olga.getId()));

        System.out.println("Messages of all user followed by John:");
        System.out.println(messageDao.getAllMessagesOfUsersInChronologicalOrder(john.getFollowings()));

        messageDao.addMessage(alex,"Message from Alex");
        System.out.println(messageDao.getAllMessagesOfUsersInChronologicalOrder(john.getFollowings()));

    }
}
