package com.discwords.discwords.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Server {

    @Id
    private long server_id;
    private String server_name;

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
}
