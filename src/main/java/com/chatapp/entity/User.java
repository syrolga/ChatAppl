package com.chatapp.entity;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class User implements Serializable {
    static private AtomicInteger idGen = new AtomicInteger(0);
    private int id;
    private String nickname;
    private String email;
    private List<Message> messages = new ArrayList<>();
    private Instant registrationDate;
    private Instant lastUpdateDate;
    private Set<User> followings = new HashSet<>();


    protected User() {
        this.id = generateId();
        this.registrationDate = Instant.now();
        this.lastUpdateDate = Instant.now();
    }

    public User(String nickname, String email) {
        this.id = generateId();
        this.nickname = nickname;
        this.email = email;
        this.registrationDate = Instant.now();
        this.lastUpdateDate = Instant.now();

    }

    private int generateId() {
        return idGen.getAndIncrement();
    }

    public int getId() {
        return id;
    }

    public void setId() {
        generateId();
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setRegistrationDate(Instant registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Instant getRegistrationDate() {
        return registrationDate;
    }

    public void setLastUpdateDate(Instant lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Instant getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setFollowings(Set<User> followings) {
        this.followings = followings;
    }

    public Set<User> getFollowings() {
        return followings;
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("User: [ id: ");
        res.append(id);
        res.append(", nickname: ");
        res.append(nickname);
        res.append(", email: ");
        res.append(email);
        res.append(", messages: ");
        res.append(messages);
        res.append(", registrationDate: ");
        res.append(registrationDate);
        res.append(", lastUpdateDate: ");
        res.append(lastUpdateDate);
        res.append(", followings: ");
        res.append("]");
        return res.toString();
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        boolean result = false;
        if (obj instanceof User) {
            User that = (User) obj;
            return this.id == that.id && this.getNickname().equals(that.getNickname()) &&
                    this.getEmail().equals(that.getEmail()) &&
                    this.getRegistrationDate().equals(that.getRegistrationDate()) &&
                    this.getLastUpdateDate().equals(that.getLastUpdateDate()) &&
                    this.getFollowings().equals(that.getFollowings()) &&
                    this.getMessages().equals(that.messages);
        }
        return result;

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + nickname.hashCode();
        result = prime * result + email.hashCode();
        result = prime * result + ((messages == null) ? 0 : messages.hashCode());
        // result = prime * result + ((followings == null) ? 0 : followings.hashCode());
        result = prime * result + ((registrationDate == null) ? 0 : registrationDate.hashCode());
        result = prime * result + ((lastUpdateDate == null) ? 0 : lastUpdateDate.hashCode());
        return result;
    }
}
