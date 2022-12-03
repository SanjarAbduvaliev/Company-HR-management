package com.example.company_hr_management.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegesterDto {
    @NotEmpty(message = "ismingizni kirimadingiz")
    @Size(min = 2,max = 25)
    private String firstName;
    @Size(min = 2,max = 35)
    @NotEmpty(message = "familiya kiritilsin")
    private String lastName;
    @NotBlank(message = "emailni kiriting")
    @Email
    private String username;
    @NotBlank(message = "password kiritilsin")
    private String password;
}
