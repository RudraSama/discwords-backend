package com.discwords.discwords.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class Profile {
   @Id
   private long profile_id;
   private long user_id;
   private String username;
   private String picture_url;
   private String email;

   public Profile(){}

    public Profile(long profile_id, long user_id, String username, String email, String picture_url) {
        this.profile_id = profile_id;
        this.user_id = user_id;
        this.username = username;
        this.picture_url = picture_url;
        this.email = email;
    }

    public long getProfileId() {
        return profile_id;
    }

    public void setProfileId(long profile_id) {
        this.profile_id = profile_id;
    }

    public long getUserId() {
        return user_id;
    }

    public void setUserId(long user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPictureUrl() {
        return picture_url;
    }

    public void setPictureUrl(String picture_url) {
        this.picture_url = picture_url;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
