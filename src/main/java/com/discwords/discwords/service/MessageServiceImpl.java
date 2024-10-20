package com.discwords.discwords.service;

import com.discwords.discwords.DTOs.ConversationDTO;
import com.discwords.discwords.DTOs.MessageDTO;
import com.discwords.discwords.model.Message;
import com.discwords.discwords.repository.ConversationRepo;
import com.discwords.discwords.repository.MessageRepo;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService{

    private final MessageRepo messageRepo;
    private final ConversationRepo conversationRepo;

    public MessageServiceImpl(MessageRepo messageRepo, ConversationRepo conversationRepo){
        this.messageRepo = messageRepo;
        this.conversationRepo = conversationRepo;
    }

    @Override
    @Async
    public void saveMessage(MessageDTO messageDTO, Boolean isConversation){
        Message message = new Message();
        message.setSender_id(messageDTO.getSender_profile().getProfileId());
        message.setMessage(messageDTO.getMessage());
        message.setTimestamp(messageDTO.getTimestamp());

        if(isConversation){
            message.setConversation_id(messageDTO.getConversation_id());
            message.setChannel_id(-1); //cannot set Channel ID to null
        }
        else{
            message.setChannel_id(messageDTO.getChannel_id());
            message.setConversation_id(-1); //cannot set Conversation ID to null
        }

        messageRepo.save(message);


    }

    @Override
    public List<MessageDTO> handleFetchConversationMessages(long profileId, long conversation_id){
        ConversationDTO conversationDTO = conversationRepo.findConversation(profileId, conversation_id);
        if(conversationDTO == null){
            return null;
        }
        return messageRepo.findConversationMessages(conversation_id);
    }
}
