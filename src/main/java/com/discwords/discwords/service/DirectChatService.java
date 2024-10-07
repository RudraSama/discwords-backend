package com.discwords.discwords.service;

import com.discwords.discwords.DTOs.DirectMessageDTO;

public interface DirectChatService {

    public void sendMessageToConv(DirectMessageDTO messageDTO, String conversation_id, String receiver_id);

}
