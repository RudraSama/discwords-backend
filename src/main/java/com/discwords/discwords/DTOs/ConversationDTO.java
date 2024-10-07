package com.discwords.discwords.DTOs;

import com.discwords.discwords.model.DirectMessage;
import com.discwords.discwords.model.Profile;

import java.util.List;

public class ConversationDTO {

    private long conversation_id;
    private Profile profile;
    private Profile profile2;
    private List<DirectMessage> directMessages;

    public ConversationDTO(){}

    public ConversationDTO(long conversation_id, Profile profile, Profile profile2, List<DirectMessage> directMessages) {
        this.conversation_id = conversation_id;
        this.profile = profile;
        this.profile2 = profile2;
        this.directMessages = directMessages;
    }

    public long getConversation_id() {
        return conversation_id;
    }

    public void setConversation_id(long conversation_id) {
        this.conversation_id = conversation_id;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Profile getProfile2() {
        return profile2;
    }

    public void setProfile2(Profile profile2) {
        this.profile2 = profile2;
    }

    public List<DirectMessage> getDirectMessages() {
        return directMessages;
    }

    public void setDirectMessages(List<DirectMessage> directMessages) {
        this.directMessages = directMessages;
    }
}
