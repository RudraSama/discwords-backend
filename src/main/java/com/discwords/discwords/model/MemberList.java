package com.discwords.discwords.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class MemberList {

    @Id
    private long id;
    private long server_id;
    private long profile_id;

    public MemberList(long id, long server_id, long profile_id) {
        this.id = id;
        this.server_id = server_id;
        this.profile_id = profile_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getServer_id() {
        return server_id;
    }

    public void setServer_id(long server_id) {
        this.server_id = server_id;
    }

    public long getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(long profile_id) {
        this.profile_id = profile_id;
    }
}
