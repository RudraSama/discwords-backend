package com.discwords.discwords.controller;

import com.discwords.discwords.DTOs.ConversationDTO;
import com.discwords.discwords.repository.ConversationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HomeController {

    @Autowired
    ConversationRepo conversationRepo;

    @GetMapping("/")
    public String greet(){
        return "Hii";
    }

//    @GetMapping("/fetch")
//    public List<ConversationDTO> fetch(){
//        return conversationRepo.findConversations(485420070744L);
//    }

    @GetMapping("/fetch")
    public ConversationDTO fetch(){
        return conversationRepo.findConversation(1, 485420070744L);
    }
}
