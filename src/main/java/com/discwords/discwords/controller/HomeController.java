package com.discwords.discwords.controller;
import com.discwords.discwords.model.ConversationDTO;
import com.discwords.discwords.model.DirectMessageDTO;
import com.discwords.discwords.repository.DirectMessageRepo;
import com.discwords.discwords.service.DirectMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HomeController {

    @Autowired
    DirectMessageService directMessageService;

    @GetMapping("/")
    public String greet(){
        return "Hii";
    }


    //temp create convo
    @PostMapping("/createConversation")
    public ConversationDTO createConversation(@RequestBody TempClass tempClass){
        System.out.println(tempClass.getProfile_id_one()+"  "+tempClass.getProfile_id_two());
        return directMessageService.createConversation(tempClass.getProfile_id_one(), tempClass.getProfile_id_two());
    }

    //temp send message
    @PostMapping("/sendMessage")
    public void sendMessage(@RequestBody DirectMessageDTO directMessageDTO){
        directMessageService.sendMessage(directMessageDTO.getConversation_id(), directMessageDTO.getProfile_id(), directMessageDTO.getMessage());
    }

    //temp get messages
    @GetMapping("/getMessages/{conversation_id}")
    public List<DirectMessageDTO> getMessages(@PathVariable  long conversation_id){

        List<DirectMessageDTO> messages = directMessageService.getMessages(conversation_id);
        System.out.println("Profile ID   "+messages.get(0).getProfile().getProfile_id());
        return messages;
    }

}
