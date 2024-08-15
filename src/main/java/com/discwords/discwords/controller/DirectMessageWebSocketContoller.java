package com.discwords.discwords.controller;

import com.discwords.discwords.model.DirectMessage;
import com.discwords.discwords.model.DirectMessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;


@Controller
public class DirectMessageWebSocketContoller {


    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

//    @MessageMapping("/chat.sendMessage")
//    public void sendMessage(ChatMessage chatMessage){
//        System.out.println(chatMessage.getConversation_id());
//        simpMessagingTemplate.convertAndSend("/topic/"+chatMessage.getConversation_id(), chatMessage);
//
//    }
}
