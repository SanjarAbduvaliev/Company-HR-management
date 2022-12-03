package com.example.company_hr_management.repository;

import com.example.company_hr_management.entity.MonthEmployed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MonthEmployedRepository extends JpaRepository<MonthEmployed, Integer> {

}
