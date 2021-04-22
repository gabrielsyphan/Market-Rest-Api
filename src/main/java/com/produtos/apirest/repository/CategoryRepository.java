package com.produtos.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.produtos.apirest.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	Category findByid(long id);
}
