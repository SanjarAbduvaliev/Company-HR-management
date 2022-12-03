package com.example.company_hr_management.payload;

import lombok.Data;

@Data
public class LoginDto {
    private String username;
    private String password;
}
