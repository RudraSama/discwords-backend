package com.discwords.discwords.repository;


import com.discwords.discwords.model.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelRepo extends JpaRepository<Channel,Long> {

}
