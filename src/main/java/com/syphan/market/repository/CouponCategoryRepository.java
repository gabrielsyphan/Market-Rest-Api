package com.syphan.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.syphan.market.models.CouponCategory;

public interface CouponCategoryRepository extends JpaRepository<CouponCategory, Long> {
	
	CouponCategory findByid(int id);
}
