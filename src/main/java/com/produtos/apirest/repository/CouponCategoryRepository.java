package com.produtos.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.produtos.apirest.models.CouponCategory;

public interface CouponCategoryRepository extends JpaRepository<CouponCategory, Long> {
	
	CouponCategory findByid(int id);
}
