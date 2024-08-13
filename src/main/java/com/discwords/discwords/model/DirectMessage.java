package com.discwords.discwords.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class DirectMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long directMessage_id;
    private String message;
    private Date timestamp;

    @ManyToOne
    @JoinColumn(name = "conversation_id")
    @JsonBackReference
    private Conversation conversation;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    @JsonBackReference
    private Profile profile;

    public DirectMessage(){}

    public DirectMessage(long directMessage_id, String message, Date timestamp, Conversation conversation, Profile profile) {
        this.directMessage_id = directMessage_id;
        this.message = message;
        this.timestamp = timestamp;
        this.conversation = conversation;
        this.profile = profile;
    }

    public long getDirectMessage_id() {
        return directMessage_id;
    }

    public void setDirectMessage_id(long directMessage_id) {
        this.directMessage_id = directMessage_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
