package com.discwords.discwords.service;

import com.discwords.discwords.model.DirectMessageDTO;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
public class DirectChatServiceImpl implements DirectChatService {


    private final SimpMessageSendingOperations simpMessageSendingOperations;

    public DirectChatServiceImpl(SimpMessageSendingOperations simpMessageSendingOperations) {
        this.simpMessageSendingOperations = simpMessageSendingOperations;
    }

    @Override
    public void sendMessageToConv(DirectMessageDTO messageDTO, String conversation_id, String receiver_id){
        simpMessageSendingOperations.convertAndSend("/topic/conversation/"+conversation_id + "/" + receiver_id, messageDTO);
    }
}


