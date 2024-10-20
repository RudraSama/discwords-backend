package com.discwords.discwords.repository;

import com.discwords.discwords.DTOs.FetchFriendRequestDTO;
import com.discwords.discwords.model.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendRequestRepo extends JpaRepository<FriendRequest, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM friend_request WHERE id = :friendRequestId AND :profileId IN (sender_id, receiver_id);")
    public Optional<FriendRequest> findById(long profileId, long friendRequestId);

    @Query("SELECT new com.discwords.discwords.DTOs.FetchFriendRequestDTO(fr.id, fr.sender_id, fr.receiver_id, p.username, p.picture_url) FROM FriendRequest fr JOIN Profile p ON fr.sender_id = p.profile_id where fr.receiver_id = :profile_id OR fr.sender_id = :profile_id")
    public List<FetchFriendRequestDTO> findFriendRequests(@Param("profile_id") long profile_id);
}
