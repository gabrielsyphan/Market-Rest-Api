package com.syphan.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.syphan.market.models.DiscountType;

public interface DiscountTypeRepository extends JpaRepository<DiscountType, Long> {

	DiscountType findByid(int id);
}
