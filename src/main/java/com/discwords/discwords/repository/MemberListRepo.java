package com.discwords.discwords.repository;

import com.discwords.discwords.model.MemberList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberListRepo extends JpaRepository<MemberList, Long> {
}
