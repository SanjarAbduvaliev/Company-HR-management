package com.example.company_hr_management.entity.turniket;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Turniket {
    @Id
    @GeneratedValue
    private UUID id;
    @OneToMany
    private Set<InputWorker>  inputsWorker;
    @OneToMany
    private Set<OutputWorker>  outputsWorker;
    private boolean acrive;
}
