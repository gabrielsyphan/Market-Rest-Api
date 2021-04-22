package com.produtos.apirest.repository;

import com.produtos.apirest.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    
    User findByid(int id);
    User findByEmail(String email);
}
