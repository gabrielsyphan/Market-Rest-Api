package com.syphan.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.syphan.market.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	Product findByid(int id);
}
