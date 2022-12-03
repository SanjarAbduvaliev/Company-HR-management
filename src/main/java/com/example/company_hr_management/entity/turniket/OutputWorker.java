package com.example.company_hr_management.entity.turniket;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OutputWorker {
    @Id
    @GeneratedValue
    private UUID id;
    
    @Column(nullable = false)
    private Timestamp timestamp;
    private boolean active;
}
