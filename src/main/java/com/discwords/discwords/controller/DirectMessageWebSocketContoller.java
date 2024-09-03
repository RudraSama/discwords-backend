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

<<<<<<< HEAD:src/main/java/com/discwords/discwords/controller/DirectMessageWebSocketController.java
    @Autowired
    private DirectChatServiceImpl directChatService;

    @MessageMapping("/queue/conversation")
    public DirectMessageDTO sendMessageToConvId(@Payload DirectMessageDTO messageDTO){

        directChatService.sendMessageToConv(messageDTO);
        return messageDTO;

    }
=======

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

//    @MessageMapping("/chat.sendMessage")
//    public void sendMessage(ChatMessage chatMessage){
//        System.out.println(chatMessage.getConversation_id());
//        simpMessagingTemplate.convertAndSend("/topic/"+chatMessage.getConversation_id(), chatMessage);
//
//    }
>>>>>>> parent of 941e199 (kuch bhi):src/main/java/com/discwords/discwords/controller/DirectMessageWebSocketContoller.java
}
