package com.discwords.discwords.DTOs;

import com.discwords.discwords.model.DirectMessage;
import com.discwords.discwords.model.Profile;

import java.util.List;

public class ConversationDTO {

    private long conversation_id;
    private long profile_id1;
    private long profile_id2;

    private Profile friend_profile;

    public ConversationDTO(){}

    public ConversationDTO(long conversation_id, long profile_id1, long profile_id2) {
        this.conversation_id = conversation_id;
        this.profile_id1 = profile_id1;
        this.profile_id2 = profile_id2;
    }

    //constructor to get friend_profile from DB
    public ConversationDTO(long conversation_id, long profile_id, long user_id, String username, String email, String picture_url){
        this.conversation_id = conversation_id;
        this.friend_profile = new Profile(profile_id, user_id, username, email, picture_url);
    }

    public long getConversation_id() {
        return conversation_id;
    }

    public void setConversation_id(long conversation_id) {
        this.conversation_id = conversation_id;
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

    public Profile getFriend_profile(){
        return friend_profile;
    }
}
