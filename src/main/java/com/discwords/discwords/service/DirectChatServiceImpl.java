package com.discwords.discwords.service;

import com.discwords.discwords.model.DirectMessageDTO;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
public class DirectChatServiceImpl implements DirectChatService {


    private SimpMessageSendingOperations simpMessageSendingOperations;

    public DirectChatServiceImpl(SimpMessageSendingOperations simpMessageSendingOperations) {
        this.simpMessageSendingOperations = simpMessageSendingOperations;
    }

    @Override
    public void sendMessageToConvId(DirectMessageDTO messageDTO){

        long conversation_id = messageDTO.getConversation_id();
        System.out.println(messageDTO.getMessage() + " " + messageDTO.getProfile_id());

        simpMessageSendingOperations.convertAndSend("/topic/conversation/" + conversation_id, messageDTO);

    }
}

