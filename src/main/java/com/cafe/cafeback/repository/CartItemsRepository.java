package com.cafe.cafeback.repository;

import com.cafe.cafeback.entity.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemsRepository extends JpaRepository<CartItems , Integer> {
}
