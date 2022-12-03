package com.example.company_hr_management.security.jwt;

import com.example.company_hr_management.entity.Role;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Set;

@Component
public class JwtProvider {
    private static final String secretKey="maxfiysuz";
    public String generateToken(String username, Set<Role> roles){

        String token = Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 30L))
                .claim("roles",roles)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
        return "Bearer "+token;

    }
    public String parserToken(String token){
        try {

            return Jwts
                    .parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        }
        catch (Exception e){
            return null;
        }
    }
}
