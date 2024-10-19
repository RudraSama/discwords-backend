package com.discwords.discwords.controller;


import com.discwords.discwords.DTOs.MessageDTO;
import com.discwords.discwords.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService){
        this.messageService = messageService;
    }

    @GetMapping("/api/fetchMessages/conversation/{id}")
    public ResponseEntity<List<MessageDTO>> fetchConversationMessages(@PathVariable long id){
        return new ResponseEntity<>(messageService.handleFetchConversationMessages(id), HttpStatus.ACCEPTED);
    }
}
