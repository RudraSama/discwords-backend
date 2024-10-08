package com.discwords.discwords.DTOs;

public class FriendRequestDTO {
    private long profile_id;
    private String username;

    public FriendRequestDTO(){};

    public FriendRequestDTO(String username, long profile_id) {
        this.username = username;
        this.profile_id = profile_id;
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
}
