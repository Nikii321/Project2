package com.example.untitled13.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JWTUtils {

    private String secret;
    private long expirationTime;
    private Key key;

    public JWTUtils(@Value("${authorization.jjwt.secret}") String secret,
                    @Value("${authorization.jjwt.expirationTime}") long expirationTime) {
        this.secret = secret;
        this.expirationTime = expirationTime;
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Date getExpirationDateFromToken(String token) {
        return getAllClaimsFromToken(token).getExpiration();
    }

    private boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

    public String generateToken(String username, String password, List<String> roles) {
        return doGenerateToken(username, password, roles);
    }

    private String doGenerateToken(String username, String password, List<String> roles) {
        long expirationTimeLong = this.expirationTime;
        Date createdDate = new Date();
        Date expirationDate = new Date(createdDate.getTime() + expirationTimeLong * 10000);
        Map<String, Object> map = new HashMap<>();
        map.put("role", roles);
        return Jwts.builder()
                .setId(username)
                .setSubject(password)
                .setIssuedAt(createdDate)
                .addClaims(map)
                .setExpiration(expirationDate)
                .signWith(key)
                .compact();
    }
}

