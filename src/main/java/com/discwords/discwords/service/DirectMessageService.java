package com.discwords.discwords.service;


import com.discwords.discwords.model.ConversationDTO;
import com.discwords.discwords.model.DirectMessageDTO;

import java.util.List;

public interface DirectMessageService {

    public ConversationDTO createConversation(long profile_id_one, long profile_id_two);
    public ConversationDTO getConversation(long conversation_id);
    public void saveMessage(long conversation_id, long profile_id, String message);
    public List<DirectMessageDTO> getMessages(long conversation_id);

}
