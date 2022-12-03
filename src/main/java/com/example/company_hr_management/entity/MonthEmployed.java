package com.example.company_hr_management.entity;

import com.example.company_hr_management.entity.enums.Month;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MonthEmployed {
    @Id
    @GeneratedValue

    private Integer id;
    @Enumerated(EnumType.STRING)
    private Month month;
}
