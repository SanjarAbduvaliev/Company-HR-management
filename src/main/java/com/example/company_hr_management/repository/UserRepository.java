package com.example.company_hr_management.repository;

import com.example.company_hr_management.entity.Salary;
import com.example.company_hr_management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByEmail( String email);
    Optional<User> findByEmailCodeAndEmail(String emailCode, String email);
    Optional<User> findByEmail(String email);
    Set<User> findAllById(UUID id);

}
