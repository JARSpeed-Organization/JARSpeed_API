package com.jarspeed.api.security;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class RefreshTokenService {
    private Map<String, Integer> refreshTokenStore = new HashMap<>();

    public String generateRefreshToken(Integer userId) {
        String refreshToken = UUID.randomUUID().toString();
        refreshTokenStore.put(refreshToken, userId);
        return refreshToken;
    }

    public boolean validateRefreshToken(String refreshToken) {
        return refreshTokenStore.containsKey(refreshToken);
    }

    public Integer getUserIdFromRefreshToken(String refreshToken) {
        return refreshTokenStore.get(refreshToken);
    }
}
