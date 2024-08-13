package com.discwords.discwords.repository;

import com.discwords.discwords.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ServerRepo extends JpaRepository<Server, Long> {
}
