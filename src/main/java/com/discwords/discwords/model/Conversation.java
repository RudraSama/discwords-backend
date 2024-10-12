package com.discwords.discwords.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

@Entity
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private long conversation_id;
    private long profile_id1;
    private long profile_id2;

    public Conversation(long conversation_id, long profile_id1, long profile_id2) {
        this.conversation_id = conversation_id;
        this.profile_id1 = profile_id1;
        this.profile_id2 = profile_id2;
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

}
