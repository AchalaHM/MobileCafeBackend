package com.cafe.cafeback.repository;

import com.cafe.cafeback.entity.Cart;
import com.cafe.cafeback.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart , Integer> {
    Optional<Cart> findByUser(User user);
}
