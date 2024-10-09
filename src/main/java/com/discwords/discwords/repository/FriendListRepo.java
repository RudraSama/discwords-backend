package com.discwords.discwords.repository;

import com.discwords.discwords.DTOs.FetchFriendRequestDTO;
import com.discwords.discwords.model.FriendList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendListRepo extends JpaRepository<FriendList, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM friend_list WHERE (sender_id = :sender_id AND receiver_id = :receiver_id) OR (sender_id = :receiver_id AND receiver_id = :sender_id) LIMIT 1;")
    Optional<FriendList> findIfFriend(@Param("sender_id") long sender_id, @Param("receiver_id") long receiver_id);


}