package com.chatapp.entity;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;

import com.chatapp.entity.dao.impl.InputValidator;

public class Message {
    static private AtomicInteger idGen = new AtomicInteger(0);
    private int id;
    private Instant creationDate;
    private Instant lastUpdateDate;
    private String text;
    private int creatorId;

    public Message(int creatorId, String text) {
        InputValidator.validateMessage(text);
        this.id = generateId();
        this.text = text;
        this.creatorId = creatorId;
        this.creationDate = Instant.now();
        this.lastUpdateDate = Instant.now();
    }

    private int generateId() {
        return idGen.getAndIncrement();
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setLastUpdateDate(Instant lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Instant getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setText(String text) {
        InputValidator.validateMessage(text);
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public int getCreatorId() {
        return creatorId;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("Message: [ text: ");
        res.append(text);
        res.append(", creatorId: ");
        res.append(creatorId);
        res.append(", creationDate: ");
        res.append(creationDate);
        res.append(", lastUpdateDate: ");
        res.append(lastUpdateDate);
        res.append("]");
        return res.toString();
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        boolean result = false;
        if (obj instanceof Message) {
            Message that = (Message) obj;
            return this.getText().equals(that.getText()) &&
                    this.getCreatorId() == that.getCreatorId() &&
                    this.getCreationDate().equals(that.getCreationDate()) &&
                    this.getLastUpdateDate().equals(that.getLastUpdateDate());
        }
        return result;

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + text.hashCode();
        result = prime * result + creatorId;
        result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
        result = prime * result + ((lastUpdateDate == null) ? 0 : lastUpdateDate.hashCode());
        return result;
    }
}
