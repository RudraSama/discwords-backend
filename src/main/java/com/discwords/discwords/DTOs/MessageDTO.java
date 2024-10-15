package com.discwords.discwords.DTOs;

import com.discwords.discwords.model.Profile;

import java.util.Date;


//@JsonDeserialize(using = DirectMessageDeserializer.class)
public class MessageDTO {
    private long message_id;
    private long conversation_id;
    private long channel_id;
    private Profile sender_profile;
    private String message;
    private Date timestamp;


    public MessageDTO(){}

    public MessageDTO(long message_id, long conversation_id, long channel_id, Profile sender_profile, String message, Date timestamp) {
        this.message_id = message_id;
        this.conversation_id = conversation_id;
        this.channel_id = channel_id;
        this.sender_profile = sender_profile;
        this.message = message;
        this.timestamp = timestamp;
    }

    public MessageDTO(long message_id, long conversation_id, long channel_id, String message, Date timestamp, long profile_id, long user_id, String username, String email, String picture_url){
        this.message_id = message_id;
        this.conversation_id = conversation_id;
        this.channel_id = channel_id;
        this.message = message;
        this.timestamp = timestamp;
        this.sender_profile = new Profile(profile_id, user_id, username, email, picture_url);
    }

    public long getMessage_id() {
        return message_id;
    }

    public void setMessage_id(long message_id) {
        this.message_id = message_id;
    }

    public long getConversation_id() {
        return conversation_id;
    }

    public void setConversation_id(long conversation_id) {
        this.conversation_id = conversation_id;
    }

    public long getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(long channel_id) {
        this.channel_id = channel_id;
    }

    public Profile getSender_profile() {
        return sender_profile;
    }

    public void setSender_profile(Profile sender_profile) {
        this.sender_profile = sender_profile;
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



}
