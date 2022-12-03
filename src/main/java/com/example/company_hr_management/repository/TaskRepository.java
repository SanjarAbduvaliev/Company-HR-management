package com.example.company_hr_management.repository;

import com.example.company_hr_management.entity.Tasks;
import com.example.company_hr_management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Tasks, UUID> {


}
