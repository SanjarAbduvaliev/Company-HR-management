package com.example.company_hr_management.payload;

import com.example.company_hr_management.entity.enums.Month;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class SalaryDto {
    private Double amount;
    private String comment;
    private Integer monthId;
    private UUID userId;

}
