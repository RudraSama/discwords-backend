package com.discwords.discwords.model;

import java.util.List;

public class ServerDTO {

    private long server_id;
    private String server_name;
    private List<Channel> channels;
    private List<MemberList> members;


    public ServerDTO() {}

    public ServerDTO(long server_id, String server_name, List<Channel> channels, List<MemberList> members) {
        this.server_id = server_id;
        this.server_name = server_name;
        this.channels = channels;
        this.members = members;
    }


    public long getServer_id() {
        return server_id;
    }

    public void setServer_id(long server_id) {
        this.server_id = server_id;
    }

    public String getServer_name() {
        return server_name;
    }

    public void setServer_name(String server_name) {
        this.server_name = server_name;
    }

    public List<Channel> getChannels() {
        return channels;
    }

    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }

    public List<MemberList> getMembers() {
        return members;
    }

    public void setMembers(List<MemberList> members) {
        this.members = members;
    }
}
