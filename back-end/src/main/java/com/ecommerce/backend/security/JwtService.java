package com.ecommerce.backend.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET =
            "my-super-secret-key-my-super-secret-key-123456";

    private static final long EXPIRATION = 1000 * 60 * 60; // 1h

    private final Key secretKey = Keys.hmacShaKeyFor(SECRET.getBytes());

    /* =====================
       GÉNÉRATION DU TOKEN
       ===================== */
    public String generateToken(String email) {
        return Jwts.builder()
            .setSubject(email)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .compact();
    }

    /* =====================
       EXTRACTION USERNAME
       ===================== */
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    /* =====================
       VALIDATION TOKEN
       ===================== */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /* =====================
       MÉTHODES INTERNES
       ===================== */
    private boolean isTokenExpired(String token) {
        return extractAllClaims(token)
            .getExpiration()
            .before(new Date());
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .getBody();
    }
}