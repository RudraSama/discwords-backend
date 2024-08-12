package com.discwords.discwords.repository;

import com.mysql.cj.protocol.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepo extends JpaRepository<Message, Long> {
}
