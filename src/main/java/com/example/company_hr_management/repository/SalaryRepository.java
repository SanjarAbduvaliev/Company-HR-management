package com.example.company_hr_management.repository;

import com.example.company_hr_management.entity.Role;
import com.example.company_hr_management.entity.Salary;
import com.example.company_hr_management.entity.enums.RoleNames;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;
import java.util.UUID;

public interface SalaryRepository extends JpaRepository<Salary, UUID> {
    Set<Salary> findAllByUser_Id(UUID user_id);
}
