package com.discwords.discwords.repository;


import com.discwords.discwords.DTOs.ChannelDTO;
import com.discwords.discwords.model.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChannelRepo extends JpaRepository<Channel, Long> {
    @Query("SELECT new com.discwords.discwords.DTOs.ChannelDTO(c.channel_id, c.server_id, c.channel_type, c.channel_name) FROM Channel c JOIN MemberList ml ON ml.server_id = c.server_id WHERE c.server_id = :server_id AND ml.profile_id = :profile_id")
    List<ChannelDTO> fetchChannels(@Param("profile_id") long profileId, @Param("server_id") long serverId);
}
