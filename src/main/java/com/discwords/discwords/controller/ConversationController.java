package com.discwords.discwords.controller;

import com.discwords.discwords.DTOs.ConversationDTO;
import com.discwords.discwords.DTOs.MessageDTO;
import com.discwords.discwords.service.ConversationService;
import com.discwords.discwords.service.UserInteractionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ConversationController {

    private final ConversationService conversationService;

    public ConversationController(ConversationService conversationService){
        this.conversationService = conversationService;
    }

    @PostMapping("/api/createConversation/{friendProfileId}")
    public ResponseEntity<ConversationDTO> handleCreateConversation(@RequestHeader("profileId") long profileId, @PathVariable("friendProfileId") long friendProfileId){
        return new ResponseEntity<>(conversationService.handleCreateConversation(profileId, friendProfileId), HttpStatus.CREATED);
    }

    @GetMapping("/api/fetchConversations")
    public ResponseEntity<List<ConversationDTO>> handleFetchConversations(@RequestHeader("profileId") long profileId){
        return new ResponseEntity<>(conversationService.handleFetchConversations(profileId),HttpStatus.ACCEPTED);
    }

    @GetMapping("/api/fetchConversation/{conversation_id}")
    public ResponseEntity<ConversationDTO> handleFetchConversation(@RequestHeader("profileId") long profileId, @PathVariable("conversation_id") long conversation_id){
        return new ResponseEntity<>(conversationService.handleFetchConversation(profileId, conversation_id), HttpStatus.ACCEPTED);
    }


    @MessageMapping("/topic/conversation/{conversation_id}/{receiver_id}")
    public MessageDTO sendMessageToConvId(@Payload MessageDTO messageDTO, @DestinationVariable("conversation_id") String conversation_id, @DestinationVariable("receiver_id") String receiver_id){
        conversationService.sendMessageToConv(messageDTO, conversation_id, receiver_id);
        return messageDTO;
    }

}
