package com.discwords.discwords.repository;

import com.discwords.discwords.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM User WHERE email = :email LIMIT 1")
    Optional<User> findByEmail(@Param("email") String email);

}
