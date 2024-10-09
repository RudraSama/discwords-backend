package com.discwords.discwords.DTOs;


public class FetchFriendRequestDTO {

    private long id;
    private long sender_id;
    private long receiver_id;
    private String username;
    private String picture_url;

    public FetchFriendRequestDTO(){}

    public FetchFriendRequestDTO(long id, long sender_id, long receiver_id, String username, String picture_url) {
        this.id = id;
        this.sender_id = sender_id;
        this.receiver_id = receiver_id;
        this.username = username;
        this.picture_url = picture_url;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getReceiver_id() {
        return receiver_id;
    }

    public void setReceiver_id(long receiver_id) {
        this.receiver_id = receiver_id;
    }

    public long getSender_id() {
        return sender_id;
    }

    public void setSender_id(long sender_id) {
        this.sender_id = sender_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
