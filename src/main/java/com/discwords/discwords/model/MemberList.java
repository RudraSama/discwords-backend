package com.discwords.discwords.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class MemberList {

    @Id
    private long member_id;
    private long profile_id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "server_id")
    @JsonBackReference
    private Server server;

    public MemberList(){}

    public MemberList(long member_id, long profile_id, long server_id) {
        this.member_id = member_id;
        this.profile_id = profile_id;
//        this.server_id = server_id;
    }



    public long getMember_id() {
        return member_id;
    }

    public void setMember_id(long member_id) {
        this.member_id = member_id;
    }

    public long getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(long profile_id) {
        this.profile_id = profile_id;
    }


    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }
}
