package com.discwords.discwords.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long conversation_id;

    @ManyToOne
    @JoinColumn(name = "profile_id_one")
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "profile_id_two")
    private Profile profile2;


    @OneToMany(mappedBy = "conversation")
    private List<DirectMessage> directMessages;


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
