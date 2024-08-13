package com.discwords.discwords.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Profile {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private long profile_id;
   private long user_id;
   private String username;
   private String picture_url;
   private String email;


   @OneToOne(mappedBy = "profile", cascade = CascadeType.ALL)
   private Member member;

   @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
   @JsonBackReference
   private List<Conversation> conversations;

   public Profile(){}

    public Profile(long profile_id, long user_id, String username, String picture_url, String email) {
        this.profile_id = profile_id;
        this.user_id = user_id;
        this.username = username;
        this.picture_url = picture_url;
        this.email = email;
    }

    public long getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(long profile_id) {
        this.profile_id = profile_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public List<Conversation> getConversations() {
        return conversations;
    }

    public void setConversations(List<Conversation> conversations) {
        this.conversations = conversations;
    }
}
