package com.jarspeed.api.security;

import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.UUID;

@Service
public class TokenService {

    public String generateToken(Integer userId) {
        String tokenData = userId + ":" + System.currentTimeMillis() + ":" + UUID.randomUUID().toString();
        return Base64.getEncoder().encodeToString(tokenData.getBytes());
    }

    public boolean validateToken(String token) {
        try {
            String decoded = new String(Base64.getDecoder().decode(token));
            String[] parts = decoded.split(":");
            long timestamp = Long.parseLong(parts[1]);
            long currentTime = System.currentTimeMillis();
            long TOKEN_EXPIRY_TIME = 3600000 * 5; // 5 heure en millisecondes

            return (currentTime - timestamp) < TOKEN_EXPIRY_TIME;
        } catch (Exception e) {
            return false;
        }
    }
}