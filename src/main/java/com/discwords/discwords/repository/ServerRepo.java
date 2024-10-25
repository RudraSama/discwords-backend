package com.discwords.discwords.repository;

import com.discwords.discwords.DTOs.ServerDTO;
import com.discwords.discwords.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServerRepo extends JpaRepository<Server, Long> {

    @Query("SELECT new com.discwords.discwords.DTOs.ServerDTO(s.server_id, s.server_name, s.description) FROM MemberList ml JOIN Server s ON ml.server_id = s.server_id WHERE ml.profile_id = :profile_id AND ml.server_id = :server_id")
    ServerDTO fetchServer(@Param("profile_id") long profileId, @Param("server_id") long serverId);

    //using 'FROM MemberList' so that we can check 'profile_id' of user
    @Query("SELECT new com.discwords.discwords.DTOs.ServerDTO(s.server_id, s.server_name, s.description) FROM MemberList ml JOIN Server s ON ml.server_id = s.server_id WHERE ml.profile_id = :profile_id")
    List<ServerDTO> fetchServers(@Param("profile_id") long profileId);
}
