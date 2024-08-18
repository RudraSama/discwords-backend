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
    public void sendMessageToConv(DirectMessageDTO messageDTO){

        simpMessageSendingOperations.convertAndSend("/queue/conversation", messageDTO);

    }
}

