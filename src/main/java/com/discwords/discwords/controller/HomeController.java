package com.discwords.discwords.controller;

import com.discwords.discwords.DTOs.ConversationDTO;
import com.discwords.discwords.DTOs.MessageDTO;
import com.discwords.discwords.repository.ConversationRepo;
import com.discwords.discwords.repository.MessageRepo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HomeController {

    @Autowired
    MessageRepo messageRepo;

    @GetMapping("/api/hello")
    public String greet(HttpServletRequest request){
        System.out.println(request.getAttribute("profileId"));
        return "Hii";
    }

}
