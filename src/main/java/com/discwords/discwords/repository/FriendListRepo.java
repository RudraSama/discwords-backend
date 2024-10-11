package com.discwords.discwords.repository;

import com.discwords.discwords.DTOs.FetchFriendRequestDTO;
import com.discwords.discwords.model.FriendList;
import com.discwords.discwords.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendListRepo extends JpaRepository<FriendList, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM friend_list WHERE (profile_id1 = :sender_id AND profile_id2 = :receiver_id) OR (profile_id1 = :receiver_id AND profile_id2 = :sender_id) LIMIT 1;")
    Optional<FriendList> findIfFriend(@Param("sender_id") long sender_id, @Param("receiver_id") long receiver_id);

    @Query("SELECT new com.discwords.discwords.model.Profile(CAST(IF(profile_id1 = :profile_id, profile_id2, profile_id1) AS Long), user_id, username, email, picture_url) FROM FriendList JOIN Profile p ON p.profile_id = CAST(IF(profile_id1 = :profile_id, profile_id2, profile_id1) AS Long) WHERE :profile_id IN (profile_id1, profile_id2)")
    List<Profile> findFriends(@Param("profile_id") long profile_id);

}