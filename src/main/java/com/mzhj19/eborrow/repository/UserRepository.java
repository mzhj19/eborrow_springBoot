package com.mzhj19.eborrow.repository;

import com.mzhj19.eborrow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User save(User user);

    User findByEmail(String email);

}
