package com.discwords.discwords.repository;

import com.discwords.discwords.model.DirectMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectMessageRepo extends JpaRepository<DirectMessage, Long> {
}
