package com.syphan.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.syphan.market.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	Category findByid(int id);
}
