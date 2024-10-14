package com.discwords.discwords.repository;

import com.discwords.discwords.DTOs.FetchFriendRequestDTO;
import com.discwords.discwords.model.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRequestRepo extends JpaRepository<FriendRequest, Long> {
    @Query("SELECT new com.discwords.discwords.DTOs.FetchFriendRequestDTO(fr.id, fr.sender_id, fr.receiver_id, p.username, p.picture_url) FROM FriendRequest fr JOIN Profile p ON fr.sender_id = p.profile_id where fr.receiver_id = :profile_id OR fr.sender_id = :profile_id")
    List<FetchFriendRequestDTO> findFriendRequests(@Param("profile_id") long profile_id);
}
