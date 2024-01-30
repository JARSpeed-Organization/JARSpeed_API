package com.jarspeed.api.security;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * The type Refresh token service.
 */
@Service
public class RefreshTokenService {
    /**
     * The Refresh token store.
     */
    private Map<String, Integer> refreshTokenStore = new HashMap<>();

    /**
     * Generate refresh token string.
     *
     * @param userId the user id
     * @return the string
     */
    public String generateRefreshToken(final Integer userId) {
        String refreshToken = UUID.randomUUID().toString();
        refreshTokenStore.put(refreshToken, userId);
        return refreshToken;
    }

    /**
     * Validate refresh token boolean.
     *
     * @param refreshToken the refresh token
     * @return the boolean
     */
    public boolean validateRefreshToken(final String refreshToken) {
        return refreshTokenStore.containsKey(refreshToken);
    }

    /**
     * Gets user id from refresh token.
     *
     * @param refreshToken the refresh token
     * @return the user id from refresh token
     */
    public Integer getUserIdFromRefreshToken(final String refreshToken) {
        return refreshTokenStore.get(refreshToken);
    }
}
