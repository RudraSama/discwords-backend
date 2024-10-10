package com.discwords.discwords.DTOs;

public class FriendRequestDTO {
    private long profile_id;
    private String username;
    private long profile_id2;

    public FriendRequestDTO(){};

    public FriendRequestDTO(String username, long profile_id, long profile_id2) {
        this.username = username;
        this.profile_id = profile_id;
        this.profile_id2 = profile_id2;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(long profile_id) {
        this.profile_id = profile_id;
    }

    public long getProfile_id2() {
        return profile_id2;
    }

    public void setProfile_id2(long profile_id2) {
        this.profile_id2 = profile_id2;
    }
}
