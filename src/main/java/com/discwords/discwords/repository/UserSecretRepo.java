package com.discwords.discwords.repository;

import com.discwords.discwords.model.UserSecret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSecretRepo extends JpaRepository<UserSecret, Long> {
}
