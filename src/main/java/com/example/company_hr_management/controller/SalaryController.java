package com.example.company_hr_management.controller;

import com.example.company_hr_management.payload.SalaryDto;
import com.example.company_hr_management.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/salary")
public class SalaryController {

    @Autowired
    SalaryService service;

    @PreAuthorize(value = "hasAnyRole('DERECTOR')")
    @PostMapping("/add")
    public ResponseEntity<?>  add(@RequestBody SalaryDto salaryDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addSalary(salaryDto));
    }
    @PreAuthorize(value = "hasAnyRole('DERECTOR','HR_MANAGER')")
    @GetMapping
    public ResponseEntity<?> getAllSalary(@RequestBody SalaryDto salaryDto){
        return ResponseEntity.ok(service.getSalaryInfo(salaryDto));
    }
}
