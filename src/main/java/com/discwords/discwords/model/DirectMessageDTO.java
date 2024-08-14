package com.discwords.discwords.model;

import java.util.Date;

public class DirectMessageDTO {
    private long directMessage_id;
    private String message;
    private Date timestamp;
    private long profile_id;
    private long conversation_id;


    private Profile profile;
    private Conversation conversation;

    public DirectMessageDTO(){}

    public DirectMessageDTO(long directMessage_id, String message, Date timestamp, long profile_id, long conversation_id) {
        this.directMessage_id = directMessage_id;
        this.message = message;
        this.timestamp = timestamp;
        this.profile_id = profile_id;
        this.conversation_id = conversation_id;
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

    public long getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(long profile_id) {
        this.profile_id = profile_id;
    }

    public long getConversation_id() {
        return conversation_id;
    }

    public void setConversation_id(long conversation_id) {
        this.conversation_id = conversation_id;
    }


    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }
}
