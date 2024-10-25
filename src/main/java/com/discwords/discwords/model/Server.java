package com.discwords.discwords.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Server {

    @Id
    private long server_id;
    private String server_name;
    private String description;


    public Server(){}

    public Server(long server_id, String server_name, String description) {
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
