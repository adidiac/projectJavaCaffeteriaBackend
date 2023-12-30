package com.cafeteria.cafeteria.Utils;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil {

    private static final String SECRET_KEY_ADMIN = "secretAdmin";
    private static final String SECRET_KEY_USER="secretUser";
    private static final long VALIDITY_DURATION_MS = 3600000; // Token validity duration in milliseconds (e.g., 1 hour)

    public String generateTokenAdmin(String username, String role) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("role", role);

        Date now = new Date();
        Date validity = new Date(now.getTime() + VALIDITY_DURATION_MS);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY_ADMIN)
                .compact();
    }

    public String generateTokenUser(String username, String role) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("role", role);

        Date now = new Date();
        Date validity = new Date(now.getTime() + VALIDITY_DURATION_MS);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY_USER)
                .compact();
    }

    public String getUsernameUser(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY_USER).parseClaimsJws(token).getBody().getSubject();
    }

    public String getUsernameAdmin(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY_ADMIN).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateTokenUser(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY_USER).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean validateTokenAdmin(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY_ADMIN).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
}
