package com.discwords.discwords.repository;

import com.discwords.discwords.DTOs.MessageDTO;
import com.discwords.discwords.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepo extends JpaRepository<Message, Long> {

    @Query("Select new com.discwords.discwords.DTOs.MessageDTO(message_id, conversation_id, -1, message, timestamp, profile_id, user_id, username, email, picture_url) FROM Message JOIN Profile p ON p.profile_id = sender_id WHERE conversation_id = :conversation_id ORDER BY timestamp")
    public List<MessageDTO> findConversationMessages(@Param("conversation_id") long conversation_id);
}
