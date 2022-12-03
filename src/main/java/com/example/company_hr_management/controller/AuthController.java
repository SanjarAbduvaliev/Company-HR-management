package com.example.company_hr_management.controller;

import com.example.company_hr_management.payload.ApiResponse;
import com.example.company_hr_management.payload.LoginDto;
import com.example.company_hr_management.payload.RegesterDto;
import com.example.company_hr_management.repository.UserRepository;
import com.example.company_hr_management.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    UserRepository userRepository;


    @Autowired
    AuthService authService;

    @PreAuthorize(value = "hasAnyRole('DERECTOR','HR_MANAGER')")
    @PostMapping("/auth/derectorRegis")
    public ResponseEntity<?> regesterUser(@RequestBody RegesterDto regesterDto){
        return ResponseEntity.ok(authService.regesterUser(regesterDto));
    }
    @PreAuthorize(value = "hasAnyRole('DERECTOR','HR_MANAGER','WORKER')")
    @GetMapping("/auth/verifyEmail")
    public ResponseEntity<?> verifyEmail(@RequestParam String mailCode, @RequestParam String email){
        ApiResponse apiResponse=authService.verifyEmail(mailCode,email);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);

    }
    @PreAuthorize(value = "hasAnyRole('DERECTOR','HR_MANAGER','WORKER')")
    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto){
        return ResponseEntity.status(200).body(authService.login(loginDto));
    }
}
