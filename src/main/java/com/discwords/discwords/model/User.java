package com.discwords.discwords.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.UniqueConstraint;
import org.springframework.stereotype.Component;

@Entity
public class User {

    @Id
    private long user_id;
    private String email;
    @Column(unique = true)
    private String username;

    //Empty Constructor
    public User(){}

    public User(Long user_id, String email, String username){
        this.user_id = user_id;
        this.email = email;
        this.username = username;
    }

    public long getUserId() {
        return user_id;
    }

    public void setUserId(long userId) {
        this.user_id = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
