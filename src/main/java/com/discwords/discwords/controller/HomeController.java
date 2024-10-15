package com.discwords.discwords.controller;

import com.discwords.discwords.DTOs.ConversationDTO;
import com.discwords.discwords.DTOs.MessageDTO;
import com.discwords.discwords.repository.ConversationRepo;
import com.discwords.discwords.repository.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HomeController {

    @Autowired
    MessageRepo messageRepo;

    @GetMapping("/")
    public String greet(){
        return "Hii";
    }

}
