package com.example.company_hr_management.security;

import com.example.company_hr_management.entity.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;
import java.util.UUID;

public class SpringSecurityAuditAware implements AuditorAware<UUID> {
    @Override
    public Optional<UUID> getCurrentAuditor() {
        //TIZIMDA SHU USER TURIBDI
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication!=null
        &&authentication.isAuthenticated()
        &&authentication.getPrincipal().equals("anonymousUser")){
            User user = (User) authentication.getPrincipal();
            return Optional.of(user.getId());
        }
        return Optional.empty();
    }
}
