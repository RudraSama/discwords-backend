package com.discwords.discwords.repository;

import com.discwords.discwords.model.DirectMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DirectMessageRepo extends JpaRepository<DirectMessage, Long> {

    //get Direct Messages with Conversation id
    @Query(nativeQuery = true, value = "SELECT * FROM direct_message WHERE conversation_id = :conversation_id;")
    public Optional<List<DirectMessage>> findByConversationId(@Param("conversation_id") long conversation_id);

}
