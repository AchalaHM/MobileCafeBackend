package com.cafe.cafeback.repository;

import com.cafe.cafeback.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer , Integer> {
}
