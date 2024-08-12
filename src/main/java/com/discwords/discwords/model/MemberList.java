package com.discwords.discwords.model;

import jakarta.persistence.Id;
import jdk.jfr.Enabled;

@Enabled
public class MemberList {

    @Id
    private long member_id;
    private long user_id;
    private long server_id;

    public MemberList(){}

    public MemberList(long member_id, long user_id, long server_id) {
        this.member_id = member_id;
        this.user_id = user_id;
        this.server_id = server_id;
    }



    public long getMember_id() {
        return member_id;
    }

    public void setMember_id(long member_id) {
        this.member_id = member_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getServer_id() {
        return server_id;
    }

    public void setServer_id(long server_id) {
        this.server_id = server_id;
    }
}
