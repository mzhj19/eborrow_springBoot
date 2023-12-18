package com.mzhj19.eborrow.repository;

import com.mzhj19.eborrow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User save(User user);

    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

}
