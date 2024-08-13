package com.discwords.discwords.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Server {

    @Id
    private long server_id;
    private String server_name;

    @OneToMany(mappedBy = "server")
    private List<Channel> channels;

    @OneToMany(mappedBy = "server", fetch = FetchType.LAZY)
    private List<MemberList> memberLists;

    @OneToOne(mappedBy = "server", cascade = CascadeType.ALL)
    private Member member;

    public Server(){}

    public Server(long server_id, String server_name) {
        this.server_id = server_id;
        this.server_name = server_name;
    }

    public String getServer_name() {
        return server_name;
    }

    public void setServer_name(String server_name) {
        this.server_name = server_name;
    }

    public long getServer_id() {
        return server_id;
    }

    public void setServer_id(long server_id) {
        this.server_id = server_id;
    }


    public List<Channel> getChannels() {
        return channels;
    }

    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }

    public List<MemberList> getMemberLists() {
        return memberLists;
    }

    public void setMemberLists(List<MemberList> memberLists) {
        this.memberLists = memberLists;
    }
}
