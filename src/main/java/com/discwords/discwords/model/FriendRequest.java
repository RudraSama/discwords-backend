package com.discwords.discwords.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class FriendRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private long id;
    private long sender_id;
    private long receiver_id;

    public FriendRequest(){};

    public FriendRequest(long profile_id1, long profile_id2) {
        this.sender_id = profile_id1;
        this.receiver_id = profile_id2;
    }

    public FriendRequest(long profile_id1, long profile_id2, long id) {
        this.id = id;
        this.sender_id = profile_id1;
        this.receiver_id = profile_id2;
    }

    public long getSender_id() {
        return sender_id;
    }

    public void setSender_id(long sender_id) {
        this.sender_id = sender_id;
    }

    public long getReceiver_id() {
        return receiver_id;
    }

    public void setReceiver_id(long receiver_id) {
        this.receiver_id = receiver_id;
    }
}
