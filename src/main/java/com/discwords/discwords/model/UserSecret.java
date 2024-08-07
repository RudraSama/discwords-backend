package com.discwords.discwords.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UserSecret {

    @Id
    private long user_id;
    private String password;


    public UserSecret(){}

    public UserSecret(long user_id, String password) {
        this.user_id = user_id;
        this.password = password;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
