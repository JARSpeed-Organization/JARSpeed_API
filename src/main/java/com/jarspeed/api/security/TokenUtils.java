package com.jarspeed.api.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

public class TokenUtils {

    /**
     * The Token service.
     */
    @Autowired
    private TokenService tokenService;

    /**
     * The constant BEGIN_INDEX.
     */
    private static final int BEGIN_INDEX = 7;

    /**
     * Extract token string.
     *
     * @param request the request
     * @return the string
     */
    public static String extractToken(final HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(BEGIN_INDEX);
        }
        return null;
    }
}
