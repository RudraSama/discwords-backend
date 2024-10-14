package com.discwords.discwords.service;

import com.discwords.discwords.DTOs.ConversationDTO;
import com.discwords.discwords.DTOs.MessageDTO;

import java.util.List;

public interface ConversationService {

    public List<ConversationDTO> handleFetchConversations(long id);
    public ConversationDTO handleFetchConversation(long conversation_id, long profile_id);
    public void sendMessageToConv(MessageDTO messageDTO, String conversation_id, String receiver_id);

}
