package com.discwords.discwords.repository;

import com.discwords.discwords.model.MemberList;
import com.discwords.discwords.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberListRepo extends JpaRepository<MemberList, Long> {

    @Query("SELECT new com.discwords.discwords.model.Profile(p.profile_id, p.user_id, p.username, p.email, p.picture_url) FROM MemberList ml JOIN Profile p ON p.profile_id = ml.profile_id WHERE ml.server_id = :server_id")
    List<Profile> fetchMembers(@Param("server_id") long serverId);

}
