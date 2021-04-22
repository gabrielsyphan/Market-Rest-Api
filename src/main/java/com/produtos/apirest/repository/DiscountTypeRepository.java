package com.produtos.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.produtos.apirest.models.DiscountType;

public interface DiscountTypeRepository extends JpaRepository<DiscountType, Long> {

	DiscountType findByid(int id);
}
