package com.discwords.discwords.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class UserSession {

    @Id
    private int session_id;
    private long user_id;
    private String token;
    private Date sessionStartTime;
    private Date sessionEndTime;

    public UserSession(){}

    public UserSession(long user_id, String token, Date sessionStartTime, Date sessionEndTime, Integer session_id) {
        this.user_id = user_id;
        this.token = token;
        this.sessionStartTime = sessionStartTime;
        this.sessionEndTime = sessionEndTime;
        this.session_id = session_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public Date getSessionEndTime() {
        return sessionEndTime;
    }

    public void setSessionEndTime(Date sessionEndTime) {
        this.sessionEndTime = sessionEndTime;
    }

    public Date getSessionStartTime() {
        return sessionStartTime;
    }

    public void setSessionStartTime(Date sessionStartTime) {
        this.sessionStartTime = sessionStartTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getSession_id() {
        return session_id;
    }

    public void setSession_id(Integer session_id) {
        this.session_id = session_id;
    }


}
