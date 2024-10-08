package com.discwords.discwords.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class FriendList {

    @Id
    private long user1_id;
    @Id
    private long user2_id;

    public FriendList(){}

    public FriendList(long user1_id, long user2_id) {
        this.user1_id = user1_id;
        this.user2_id = user2_id;
    }

    public long getUser1_id() {
        return user1_id;
    }

    public void setUser1_id(long user1_id) {
        this.user1_id = user1_id;
    }

    public long getUser2_id() {
        return user2_id;
    }

    public void setUser2_id(long user2_id) {
        this.user2_id = user2_id;
    }
}
