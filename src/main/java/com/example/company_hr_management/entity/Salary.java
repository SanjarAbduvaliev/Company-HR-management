package com.example.company_hr_management.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Salary {

    @Id
    @GeneratedValue
    private UUID id;

    @NotEmpty(message = "Oylikni miqdorini kiritish majburiy")
    private Double amound;

    private String comment;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy.MM.dd")
    private Date getSalaryWorker;

    @NotEmpty(message = "Oylni kiritish majburiy")
    @ManyToOne
    private MonthEmployed monthEmployed;

    @CreatedBy
    private UUID createdBySalary;//oylikni kim belgilaganligi

    @LastModifiedBy
    private UUID updateBy;//oylikni kim o'zgartriganligi

    @ManyToOne
    @NotEmpty(message = "Xodimni tanlang")
    private User user;

}
