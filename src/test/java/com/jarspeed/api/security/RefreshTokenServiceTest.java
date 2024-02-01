package com.jarspeed.api.security;

import com.jarspeed.api.security.RefreshTokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Refresh token service test.
 */
class RefreshTokenServiceTest {

    /**
     * The Refresh token service.
     */
    private RefreshTokenService refreshTokenService;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        refreshTokenService = new RefreshTokenService();
    }

    /**
     * Test generate and validate refresh token.
     */
    @Test
    void testGenerateAndValidateRefreshToken() {
        Integer userId = 1;
        String refreshToken = refreshTokenService.generateRefreshToken(userId);

        assertNotNull(refreshToken);
        assertTrue(refreshTokenService.validateRefreshToken(refreshToken));
        assertEquals(userId, refreshTokenService.getUserIdFromRefreshToken(refreshToken));
    }

    /**
     * Test invalidate refresh token.
     */
    @Test
    void testInvalidateRefreshToken() {
        String invalidToken = "invalidToken";

        assertFalse(refreshTokenService.validateRefreshToken(invalidToken));
        assertNull(refreshTokenService.getUserIdFromRefreshToken(invalidToken));
    }
}
