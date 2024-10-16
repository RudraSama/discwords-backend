package com.discwords.discwords.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long message_id;

    //Message can be of either Channel or Conversation (one to one)
    private long conversation_id;
    private long channel_id;
    private long sender_id;
    private String message;
    private Date timestamp;




    public Message(){}

    public Message(long message_id, long conversation_id, long channel_id, String message, long sender_id, Date timestamp) {
        this.message_id = message_id;
        this.channel_id = channel_id;
        this.conversation_id = conversation_id;
        this.message = message;
        this.sender_id = sender_id;
        this.timestamp = timestamp;
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

    public long getSender_id() {
        return sender_id;
    }

    public void setSender_id(long sender_id) {
        this.sender_id = sender_id;
    }

}
