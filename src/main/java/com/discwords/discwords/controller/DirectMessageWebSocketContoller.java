package com.discwords.discwords.controller;

import com.discwords.discwords.model.DirectMessage;
import com.discwords.discwords.model.DirectMessageDTO;
import com.discwords.discwords.service.DirectChatServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;


@Controller
public class DirectMessageWebSocketContoller {

    @Autowired
    private DirectChatServiceImpl directChatService;

    @MessageMapping("/queue/conversation")
    public DirectMessageDTO sendMessageToConvId(@Payload DirectMessageDTO messageDTO){

        directChatService.sendMessageToConv(messageDTO);
        return messageDTO;

    }

}
