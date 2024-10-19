package com.discwords.discwords.repository;

import com.discwords.discwords.DTOs.ConversationDTO;
import com.discwords.discwords.model.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConversationRepo extends JpaRepository<Conversation, Long> {

    @Query("SELECT new com.discwords.discwords.DTOs.ConversationDTO(conversation_id, CAST(IF(profile_id1 = :profile_id, profile_id2, profile_id1) AS Long), user_id, username, email, picture_url) FROM Conversation JOIN Profile p ON p.profile_id = CAST(IF(profile_id1 = :profile_id, profile_id2, profile_id1) AS Long) WHERE conversation_id = :conversation_id AND :profile_id IN (profile_id1, profile_id2)")
    ConversationDTO findConversation(@Param("profile_id") long profile_id, @Param("conversation_id") long conversation_id);

    @Query("SELECT new com.discwords.discwords.DTOs.ConversationDTO(conversation_id, CAST(IF(profile_id1 = :profile_id, profile_id2, profile_id1) AS Long), user_id, username, email, picture_url) FROM Conversation JOIN Profile p ON p.profile_id = CAST(IF(profile_id1 = :profile_id, profile_id2, profile_id1) AS Long) WHERE :profile_id IN (profile_id1, profile_id2)")
    List<ConversationDTO> findConversations(@Param("profile_id") long profile_id);
}
