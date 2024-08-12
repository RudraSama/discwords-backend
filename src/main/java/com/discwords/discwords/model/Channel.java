package com.discwords.discwords.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Channel {
    @Id
    private long channel_id;
    private long server_id;
    private String channel_type;
    private String channel_name;

    public Channel() {
    }

    public Channel(long channel_id, long server_id, String channel_type, String channel_name) {
        this.channel_id = channel_id;
        this.server_id = server_id;
        this.channel_type = channel_type;
        this.channel_name = channel_name;
    }

    public long getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(long channel_id) {
        this.channel_id = channel_id;
    }

    public long getServer_id() {
        return server_id;
    }

    public void setServer_id(long server_id) {
        this.server_id = server_id;
    }

    public String getChannel_type() {
        return channel_type;
    }

    public void setChannel_type(String channel_type) {
        this.channel_type = channel_type;
    }

    public String getChannel_name() {
        return channel_name;
    }

    public void setChannel_name(String channel_name) {
        this.channel_name = channel_name;
    }
}
