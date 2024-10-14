package com.discwords.discwords.service;

import com.discwords.discwords.DTOs.ConversationDTO;
import com.discwords.discwords.DTOs.MessageDTO;
import com.discwords.discwords.repository.ConversationRepo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConversationServiceImpl implements ConversationService {


    private final SimpMessageSendingOperations simpMessageSendingOperations;
    private final ConversationRepo conversationRepo;

    public ConversationServiceImpl(SimpMessageSendingOperations simpMessageSendingOperations, ConversationRepo conversationRepo) {
        this.simpMessageSendingOperations = simpMessageSendingOperations;
        this.conversationRepo = conversationRepo;
    }

    @Override
    public List<ConversationDTO> handleFetchConversations(long id){
        return conversationRepo.findConversations(id);
    }

    @Override
    public ConversationDTO handleFetchConversation(long conversation_id, long profile_id){
        return conversationRepo.findConversation(conversation_id, profile_id);
    }

    @Override
    public void sendMessageToConv(MessageDTO messageDTO, String conversation_id, String receiver_id){
        simpMessageSendingOperations.convertAndSend("/topic/conversation/"+conversation_id + "/" + receiver_id, messageDTO);
    }
}


