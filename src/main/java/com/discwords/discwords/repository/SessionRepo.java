package com.discwords.discwords.repository;

import com.discwords.discwords.model.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepo extends JpaRepository<UserSession,Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM UserSession WHERE user_id = :user_id LIMIT 1")
    Optional<UserSession> findByUserId(@Param("user_id") long user_id);
}
