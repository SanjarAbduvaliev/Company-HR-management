package com.example.company_hr_management.entity;

import com.example.company_hr_management.entity.turniket.Turniket;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue

    private UUID id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

//    @Email
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false,updatable = false)
    @CreationTimestamp//qachon yaratilganligi saqlab qoyadi
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

//    @OneToMany
//    Set<Salary> salaries;
//    @ManyToOne
//    private Salary salaries;


    @CreatedBy
    private UUID createdBy;

    @LastModifiedBy
    private UUID updateBy;

    @OneToOne
    private Turniket turniket;

    @JsonIgnore
    @ManyToMany
    private List<Tasks> tasks;


    private String emailCode;

    private boolean  accountNonExpired=true; //bu userni  amal qilish muddati o'tmaganlig

    private boolean accountNonLocked=true; //bu user bloklanmaganligi

    private boolean credentialsNonExpired=true;

    private boolean enabled=false;


    /**
     * UserDetails NING OVERRIDE QILGAN MAJBURIY METODLARI
     *
     * getAuthorities  BU USERNING  HUQUQLARI RO'YHATI
     * USERNING USERNAME QAYTARUVCHI METHOD
     * ACCOUNTNI AMAL QILISH MUDDATI TUGAMAGANLIGINI QAYTARADI
     * ACCOUNT BLOKLANGAN YOKI BLOKLANMAGANLIGI HOLATI
     * ACCOUNTNI ISHONCHLILIK MUDDATI TUGAGAN YOKI TUGAMAGANLIGI
     * ACCOUNTNI ACTIVE YOKI ACTIVMASLIGINI BILDIRUVCHI METHOT
     * @return
     */

    //getAuthorities  BU USERNING  HUQUQLARI RO'YHATI
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;

    }

    //USERNING USERNAME QAYTARUVCHI METHOD
    @Override
    public String getUsername() {
        return this.email;
    }

    //ACCOUNTNI AMAL QILISH MUDDATI TUGAMAGANLIGINI QAYTARADI
    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    //ACCOUNT BLOKLANGAN YOKI BLOKLANMAGANLIGI HOLATI
    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    //ACCOUNTNI ISHONCHLILIK MUDDATI TUGAGAN YOKI TUGAMAGANLIGI
    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    //ACCOUNTNI ACTIVE YOKI ACTIVMASLIGINI BILDIRUVCHI METHOT
    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
