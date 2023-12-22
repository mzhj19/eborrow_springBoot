package com.mzhj19.eborrow.repository;

import com.mzhj19.eborrow.model.EborrowUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<EborrowUser, Long> {
    EborrowUser save(EborrowUser eborrowUser);

    Optional<EborrowUser> findByEmail(String email);

    Boolean existsByEmail(String email);

}
