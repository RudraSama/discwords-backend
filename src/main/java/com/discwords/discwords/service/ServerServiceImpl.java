package com.discwords.discwords.service;


import com.discwords.discwords.DTOs.ChannelDTO;
import com.discwords.discwords.DTOs.ServerDTO;
import com.discwords.discwords.model.Profile;
import com.discwords.discwords.repository.ChannelRepo;
import com.discwords.discwords.repository.MemberListRepo;
import com.discwords.discwords.repository.ServerRepo;
import org.springframework.stereotype.Service;

import com.discwords.discwords.DTOs.ServerDTO;

import java.util.List;

@Service
public class ServerServiceImpl implements ServerService{

    private final ServerRepo serverRepo;
    private final ChannelRepo channelRepo;
    private final MemberListRepo memberListRepo;

    public ServerServiceImpl(ServerRepo serverRepo, ChannelRepo channelRepo, MemberListRepo memberListRepo){
        this.serverRepo = serverRepo;
        this.channelRepo = channelRepo;
        this.memberListRepo = memberListRepo;
    }


    @Override
    public ServerDTO getServer(long profileId, long serverId){
        return serverRepo.fetchServer(profileId, serverId);
    }

    @Override
    public List<ServerDTO> getServers(long profileId){
        return serverRepo.fetchServers(profileId);
    }

    @Override
    public List<ChannelDTO> getChannels(long profileId, long serverId){
        return channelRepo.fetchChannels(profileId, serverId);
    }

    @Override
    public List<Profile> getMembers(long profileId, long serverId){
        return memberListRepo.fetchMembers(serverId);
    }

}
