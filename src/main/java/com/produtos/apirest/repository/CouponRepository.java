package com.produtos.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.produtos.apirest.models.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

	Coupon findByid(int id);
}
