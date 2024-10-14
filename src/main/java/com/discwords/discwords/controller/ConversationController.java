package com.discwords.discwords.controller;

import com.discwords.discwords.DTOs.ConversationDTO;
import com.discwords.discwords.DTOs.MessageDTO;
import com.discwords.discwords.service.ConversationService;
import com.discwords.discwords.service.UserInteractionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ConversationController {

    private final ConversationService conversationService;

    public ConversationController(ConversationService conversationService){
        this.conversationService = conversationService;
    }

    @GetMapping("/api/fetchConversations/{id}")
    public ResponseEntity<List<ConversationDTO>> handleFetchConversations(@PathVariable long id){
        return new ResponseEntity<>(conversationService.handleFetchConversations(id), HttpStatus.ACCEPTED);
    }

    @GetMapping("/api/fetchConversation/{conversation_id}/{profile_id}")
    public ResponseEntity<ConversationDTO> handleFetchConversation(@PathVariable long conversation_id, @PathVariable("profile_id") long profile_id){
        return new ResponseEntity<>(conversationService.handleFetchConversation(conversation_id, profile_id), HttpStatus.ACCEPTED);
    }

    @MessageMapping("/topic/conversation/{conversation_id}/{receiver_id}")
    public MessageDTO sendMessageToConvId(@Payload MessageDTO messageDTO, @DestinationVariable("conversation_id") String conversation_id, @DestinationVariable("receiver_id") String receiver_id){
        conversationService.sendMessageToConv(messageDTO, conversation_id, receiver_id);
        return messageDTO;
    }

}
