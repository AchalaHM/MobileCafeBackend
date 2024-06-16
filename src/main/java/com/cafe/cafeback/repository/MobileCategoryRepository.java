package com.cafe.cafeback.repository;

import com.cafe.cafeback.entity.MobilesCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MobileCategoryRepository extends JpaRepository<MobilesCategory , Integer> {
}
