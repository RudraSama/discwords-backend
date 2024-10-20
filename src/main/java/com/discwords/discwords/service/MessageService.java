package com.discwords.discwords.service;

import com.discwords.discwords.DTOs.MessageDTO;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MessageService {

    // if isConversation true, Message is for 1-1 conversation, else Channel
    @Async
    public void saveMessage(MessageDTO messageDTO, Boolean isConversation);
    public List<MessageDTO> handleFetchConversationMessages(long profileId, long conversation_id);
}
