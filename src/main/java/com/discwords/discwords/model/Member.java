package com.discwords.discwords.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Member {
    @Id
    private long member_id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "server_id", nullable = false)
    private Server server;


    public Member() {}

    public long getMember_id(){
        return member_id;
    }

    public void setMember_id(long member_id) {
        this.member_id = member_id;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }
}
