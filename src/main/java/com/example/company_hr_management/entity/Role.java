package com.example.company_hr_management.entity;

import com.example.company_hr_management.entity.enums.RoleNames;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    @Enumerated(EnumType.STRING)
    private RoleNames roleName;

    @Override
    public String getAuthority() {
        return this.roleName.name();
    }
}
