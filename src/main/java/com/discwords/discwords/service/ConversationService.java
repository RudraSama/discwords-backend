package com.discwords.discwords.service;

import com.discwords.discwords.DTOs.ConversationDTO;
import com.discwords.discwords.DTOs.MessageDTO;

import java.util.List;

public interface ConversationService {

    public ConversationDTO handleCreateConversation(ConversationDTO conversationDTO);
    public List<ConversationDTO> handleFetchConversations(long id);
    public ConversationDTO handleFetchConversation(long profile_id, long conversation_id);
    public void sendMessageToConv(MessageDTO messageDTO, String conversation_id, String receiver_id);

}
