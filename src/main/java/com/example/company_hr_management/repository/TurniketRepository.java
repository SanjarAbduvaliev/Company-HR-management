package com.example.company_hr_management.repository;

import com.example.company_hr_management.entity.turniket.Turniket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TurniketRepository extends JpaRepository<Turniket, UUID> {
}
