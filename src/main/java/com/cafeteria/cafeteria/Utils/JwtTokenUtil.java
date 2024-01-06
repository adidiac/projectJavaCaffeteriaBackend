package com.cafeteria.cafeteria.Utils;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenUtil {
    // keys need to be at least 256 bits long - 32 bytes
    private static final String SECRET_KEY_ADMIN = "secretAdmin000000000000000000000";
    private static final String SECRET_KEY_USER="secretUser0000000000000000000000";
    private static final long VALIDITY_DURATION_MS = 3600000; // Token validity duration in milliseconds (e.g., 1 hour)

    public String generateTokenAdmin(String username, String role) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("role", role);

        Date now = new Date();
        Date validity = new Date(now.getTime() + VALIDITY_DURATION_MS);
        Key SECRET_ADMIN = Keys.hmacShaKeyFor(SECRET_KEY_ADMIN.getBytes());
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SECRET_ADMIN)
                .compact();
    }

    public String generateTokenUser(String username, String role) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("role", role);

        Date now = new Date();
        Date validity = new Date(now.getTime() + VALIDITY_DURATION_MS);
        if (role.equals("admin")) {
            Key SECRET_ADMIN = Keys.hmacShaKeyFor(SECRET_KEY_ADMIN.getBytes());
            return Jwts.builder()
                    .setClaims(claims)
                    .setIssuedAt(now)
                    .setExpiration(validity)
                    .signWith(SECRET_ADMIN)
                    .compact();
        }
        Key SECRET_USER = Keys.hmacShaKeyFor(SECRET_KEY_USER.getBytes());
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SECRET_USER)
                .compact();
    }

    public String getUsernameUser(String token) {
        try {
            Key SECRET_USER = Keys.hmacShaKeyFor(SECRET_KEY_USER.getBytes());
            var jwtparsed=Jwts.parserBuilder().setSigningKey(SECRET_USER).build().parseClaimsJws(token);
            var body = jwtparsed.getBody();
            return body.getSubject();
        } catch (Exception e) {
            return null;
        }
    }

    public String getUsernameAdmin(String token) {
        Key SECRET_ADMIN = Keys.hmacShaKeyFor(SECRET_KEY_ADMIN.getBytes());
        return Jwts.parserBuilder().setSigningKey(SECRET_ADMIN).build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateTokenUser(String token) {
        try {
            Key SECRET_USER = Keys.hmacShaKeyFor(SECRET_KEY_USER.getBytes());
            Jwts.parserBuilder().setSigningKey(SECRET_USER).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean validateTokenAdmin(String token) {
        try {
            Key SECRET_ADMIN = Keys.hmacShaKeyFor(SECRET_KEY_ADMIN.getBytes());
            Jwts.parserBuilder().setSigningKey(SECRET_ADMIN).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
}
