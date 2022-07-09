package com.example.numbersapi.repository;

import com.example.numbersapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    boolean existsByUsername(String username);
    void deleteUserByUsername(String username);
    void deleteUserById(Long id);
}
