package com.cafe.cafeback.repository;

import com.cafe.cafeback.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User , Integer> {
    public User findByEmail(String email);
    public boolean existsByEmail(String email);
}
