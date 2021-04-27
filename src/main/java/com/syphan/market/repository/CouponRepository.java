package com.syphan.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.syphan.market.models.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

	Coupon findByid(int id);
}
