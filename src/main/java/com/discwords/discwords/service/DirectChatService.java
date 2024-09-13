package com.discwords.discwords.service;

import com.discwords.discwords.model.DirectMessageDTO;

public interface DirectChatService {

    public void sendMessageToConv(DirectMessageDTO messageDTO, String conversation_id);

}
