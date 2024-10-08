package com.discwords.discwords.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class FriendRequest {
    @Id
    private long profile_id1;
    private long profile_id2;

    public FriendRequest(long profile_id1, long profile_id2) {
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
