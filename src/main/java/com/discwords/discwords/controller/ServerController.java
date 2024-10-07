package com.discwords.discwords.controller;


import com.discwords.discwords.DTOs.ServerDTO;
import com.discwords.discwords.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ServerController {

    @Autowired
    ServerService serverService;

    @GetMapping("/getServers")
    public ResponseEntity<List<ServerDTO>> getServers(){
        return new ResponseEntity<>(serverService.getServers(), HttpStatus.ACCEPTED);
    }

}
