package com.discwords.discwords.repository;


import com.discwords.discwords.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepo extends JpaRepository<Profile, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM profile WHERE user_id = :user_id LIMIT 1;")
    Optional<Profile> findByUserId(@Param("user_id") long user_id);

    @Query(nativeQuery = true, value = "SELECT * FROM profile WHERE username = :username LIMIT 1;")
    Optional<Profile> findByUsername(@Param("username") String username);

}
