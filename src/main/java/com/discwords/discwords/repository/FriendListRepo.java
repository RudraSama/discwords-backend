package com.discwords.discwords.repository;

import com.discwords.discwords.model.FriendList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FriendListRepo extends JpaRepository<FriendList, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM friendlist WHERE (profile_id1 = :profile_id1 AND profile_id2 = :profile_id2) OR (profile_id1 = :profile_id2 AND profile_id2 = :profile_id1) LIMIT 1;")
    Optional<FriendList> findIfFriend(@Param("profile_id1") long profile_id1, @Param("profile_id2") long profile_id2);
}
