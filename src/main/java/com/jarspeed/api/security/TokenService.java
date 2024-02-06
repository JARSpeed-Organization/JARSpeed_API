package com.jarspeed.api.security;

import com.jarspeed.api.user.User;
import com.jarspeed.api.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;

/**
 * The type Token service.
 */
@Service
public class TokenService {
    /**
     * Service to manage user-related operations.
     * The Spring framework automatically injects a UserService instance.
     */
    @Autowired
    private UserRepository userRepository;
    /**
     * The constant TOKEN_EXPIRY_TIME.
     * 5 heures en millisecondes.
     */
    private static final long TOKEN_EXPIRY_TIME = 3600000 * 5;

    /**
     * Generate token string.
     *
     * @param userId the user id
     * @return the string
     */
    public String generateToken(final Integer userId) {
        String tokenData = userId + ":" + System.currentTimeMillis()
                + ":" + UUID.randomUUID().toString();
        return Base64.getEncoder()
                .encodeToString(tokenData.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Validate token boolean.
     *
     * @param token the token
     * @return the boolean
     */
    public boolean validateToken(final String token) {
        try {
            String decoded = new String(Base64.getDecoder().decode(token),
                    StandardCharsets.UTF_8);
            String[] parts = decoded.split(":");
            long timestamp = Long.parseLong(parts[1]);
            long currentTime = System.currentTimeMillis();

            return (currentTime - timestamp) < TOKEN_EXPIRY_TIME;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Gets user id from token.
     *
     * @param token the token
     * @return the user id from token
     */
    public Integer getUserIdFromToken(final String token) {
        User user = userRepository.findByToken(token);
        return user != null ? user.getId() : null;
    }
}
