package com.discwords.discwords.controller;


import com.discwords.discwords.DTOs.ChannelDTO;
import com.discwords.discwords.DTOs.ServerDTO;
import com.discwords.discwords.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@Controller
public class ServerController {

    private final ServerService serverService;

    public ServerController(ServerService serverService){
        this.serverService = serverService;
    }


    @GetMapping("/api/getServer/{server_id}")
    public ResponseEntity<ServerDTO> getServer(@RequestHeader("profileId") long profileId, @PathVariable("server_id") long server_id){
        return new ResponseEntity<>(serverService.getServer(profileId, server_id), HttpStatus.ACCEPTED);
    }

    @GetMapping("/api/getServers")
    public ResponseEntity<List<ServerDTO>> getServers(@RequestHeader("profileId") long profileId){
        return new ResponseEntity<>(serverService.getServers(profileId), HttpStatus.ACCEPTED);
    }

    @GetMapping("api/getChannels/{server_id}")
    public ResponseEntity<List<ChannelDTO>> getChannels(@RequestHeader("profileId") long profileId, @PathVariable("server_id") long serverId){
        return new ResponseEntity<>(serverService.getChannels(profileId, serverId), HttpStatus.ACCEPTED);
    }

}
