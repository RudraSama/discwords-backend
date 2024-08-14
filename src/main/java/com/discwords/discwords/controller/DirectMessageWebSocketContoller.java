package com.discwords.discwords.controller;

import com.discwords.discwords.model.DirectMessageDTO;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class DirectMessageWebSocketContoller {

    @MessageMapping("/user")
    @SendTo("/channel/1")
    public DirectMessageDTO send(DirectMessageDTO message){
        System.out.println("i'm controller");
        return message;
    }
}
