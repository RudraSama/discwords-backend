package com.discwords.discwords.service;

import com.discwords.discwords.DTOs.ChannelDTO;
import com.discwords.discwords.DTOs.ServerDTO;

import java.util.List;

public interface ServerService {

    public ServerDTO getServer(long profileId, long serverId);
    public List<ServerDTO> getServers(long profileId);
    public List<ChannelDTO> getChannels(long profileId, long serverId);
}
