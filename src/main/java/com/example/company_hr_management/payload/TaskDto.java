package com.example.company_hr_management.payload;

import com.example.company_hr_management.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Data public class TaskDto {
    @NotBlank(message = "Vazifa nomi kiritilishi majburiy")
    private String name;

    @Size(min = 60)
    @NotBlank(message = "Vazifa haqida batafsil ma'lumot bering")
    private String taskAbout;

    @NotEmpty(message = "Sanani kiriting")
    @JsonFormat(pattern = "yyyy/MMM/dd")
    private Date createdDate;

    @NotEmpty(message = "Sanani kiriting")
    @JsonFormat(pattern = "yyyy/MMM/dd")
    private Date deadline;
    private Set<UUID> usersId;
    private boolean active;
    private UUID userId;
}
