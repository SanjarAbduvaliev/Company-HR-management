package com.example.company_hr_management.repository;

import com.example.company_hr_management.entity.turniket.InputWorker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InputRepository extends JpaRepository<InputWorker, UUID> {
}
