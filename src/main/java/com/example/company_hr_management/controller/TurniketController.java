package com.example.company_hr_management.controller;

import com.example.company_hr_management.payload.turniketdto.InputOutpuDto;
import com.example.company_hr_management.service.TurniketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/turniket")
public class TurniketController {
    @Autowired
    TurniketService turniketService;
    @PreAuthorize(value = "hasAnyRole('DERECTOR','HR_MANAGER')")
    @PostMapping
    public ResponseEntity<?> add(@RequestBody InputOutpuDto inputOutpuDto){
        return ResponseEntity.ok(turniketService.addTurniket(inputOutpuDto));

    }
}
