package com.example.company_hr_management.entity;

import com.example.company_hr_management.entity.enums.Condation;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(value = AuditingEntityListener.class)
public class Tasks {
    @Id
    @GeneratedValue
    private UUID id;
    @Size(min = 10,max = 100)
    @Column(nullable = false)
    private String name;
    private String taskAbout;

    @Enumerated(value = EnumType.STRING)
    private Condation condation;

    @CreatedDate
    @Column(nullable = false)
    private Date createdTask;

    @CreatedBy
    private UUID createdByTask;
    @LastModifiedBy
    private UUID updatedByTask;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date deadline;//vazifani oxirgi muddati

    @ManyToMany
    private Set<User> users;
    private boolean active=false;
}
