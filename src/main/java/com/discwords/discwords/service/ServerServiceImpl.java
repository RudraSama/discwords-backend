package com.discwords.discwords.service;


import com.discwords.discwords.model.Channel;
import com.discwords.discwords.model.MemberList;
import com.discwords.discwords.model.Server;
import com.discwords.discwords.model.ServerDTO;
import com.discwords.discwords.repository.ServerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServerServiceImpl implements ServerService{


    //TODO - Converting it into Constructor Injection
    @Autowired
    ServerRepo serverRepo;

    @Override
    public List<ServerDTO> getServers(){
        List<ServerDTO> serverDTOList = new ArrayList<ServerDTO>();
        serverRepo.findAll().forEach(server->{
            serverDTOList.add(new ServerDTO(server.getServer_id(), server.getServer_name(), server.getChannels(), server.getMemberLists()));
        });

        return serverDTOList;

    }
}
