package com.syphan.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.syphan.market.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
    User findByEmail(String email);
}
