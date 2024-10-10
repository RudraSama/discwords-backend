package com.discwords.discwords.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class FriendList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private long id;
    private long profile_id1;
    private long profile_id2;

    public FriendList(){}

    public FriendList(long profile_id1, long profile_id2) {
        this.profile_id1 = profile_id1;
        this.profile_id2 = profile_id2;
    }

    public FriendList(long id, long profile_id1, long profile_id2) {
        this.id = id;
        this.profile_id1 = profile_id1;
        this.profile_id2 = profile_id2;
    }

    public long getProfile_id1() {
        return profile_id1;
    }

    public void setProfile_id1(long profile_id1) {
        this.profile_id1 = profile_id1;
    }

    public long getProfile_id2() {
        return profile_id2;
    }

    public void setProfile_id2(long profile_id2) {
        this.profile_id2 = profile_id2;
    }
}
