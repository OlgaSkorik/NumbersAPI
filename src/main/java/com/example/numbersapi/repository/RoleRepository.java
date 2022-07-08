package com.example.numbersapi.repository;

import com.example.numbersapi.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByTypeOfRole(String typeOfRole);
}
