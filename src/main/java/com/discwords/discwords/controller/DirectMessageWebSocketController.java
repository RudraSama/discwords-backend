package com.discwords.discwords.controller;

import com.discwords.discwords.model.DirectMessageDTO;
import com.discwords.discwords.service.DirectChatServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;


@Controller
public class DirectMessageWebSocketController {

    @Autowired
    private DirectChatServiceImpl directChatService;

    @MessageMapping("/topic/conversation/{conversation_id}/{receiver_id}")
    public DirectMessageDTO sendMessageToConvId(@Payload DirectMessageDTO messageDTO, @DestinationVariable("conversation_id") String conversation_id, @DestinationVariable("receiver_id") String receiver_id){
        System.out.println(messageDTO.getMessage());
        directChatService.sendMessageToConv(messageDTO, conversation_id, receiver_id);
        return messageDTO;
    }

}
