package com.example.company_hr_management.repository;

import com.example.company_hr_management.entity.Role;
import com.example.company_hr_management.entity.enums.RoleNames;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findByRoleName(RoleNames roleName);
}
