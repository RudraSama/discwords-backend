package com.discwords.discwords.repository;

import com.discwords.discwords.model.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConversationRepo extends JpaRepository<Conversation, Long> {
}
