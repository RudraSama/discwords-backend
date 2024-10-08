package com.discwords.discwords.repository;

import com.discwords.discwords.model.DirectMessage;
import com.discwords.discwords.model.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRequestRepo extends JpaRepository<FriendRequest, Long> {

}
