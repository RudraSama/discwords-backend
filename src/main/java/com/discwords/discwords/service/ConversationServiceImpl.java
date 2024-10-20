package com.discwords.discwords.service;

import com.discwords.discwords.DTOs.ConversationDTO;
import com.discwords.discwords.DTOs.MessageDTO;
import com.discwords.discwords.model.Conversation;
import com.discwords.discwords.repository.ConversationRepo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConversationServiceImpl implements ConversationService {


    private final SimpMessageSendingOperations simpMessageSendingOperations;
    private final ConversationRepo conversationRepo;
    private final MessageService messageService;

    public ConversationServiceImpl(SimpMessageSendingOperations simpMessageSendingOperations, ConversationRepo conversationRepo, MessageService messageService) {
        this.simpMessageSendingOperations = simpMessageSendingOperations;
        this.conversationRepo = conversationRepo;
        this.messageService = messageService;
    }

    @Override
    public ConversationDTO handleCreateConversation(long profileId, long friendProfileId){
        Conversation conversation = conversationRepo.save(new Conversation(profileId, friendProfileId));
        return new ConversationDTO(conversation.getConversation_id(),conversation.getProfile_id1(), conversation.getProfile_id2());
    }

    @Override
    public List<ConversationDTO> handleFetchConversations(long id){
        return conversationRepo.findConversations(id);
    }

    @Override
    public ConversationDTO handleFetchConversation(long profile_id, long conversation_id){
        return conversationRepo.findConversation(profile_id, conversation_id);
    }

    @Override
    public void sendMessageToConv(MessageDTO messageDTO, String conversation_id, String receiver_id){
        messageService.saveMessage(messageDTO, true);
        simpMessageSendingOperations.convertAndSend("/topic/conversation/"+conversation_id + "/" + receiver_id, messageDTO);
    }
}


