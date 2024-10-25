package com.discwords.discwords.DTOs;

import com.discwords.discwords.model.Channel;
import com.discwords.discwords.model.MemberList;

import java.util.List;

public class ServerDTO {
    private long server_id;
    private String server_name;
    private String description;


    public ServerDTO(){}

    public ServerDTO(long server_id, String server_name, String description) {
        this.server_id = server_id;
        this.server_name = server_name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
