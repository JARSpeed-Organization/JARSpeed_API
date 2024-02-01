package com.jarspeed.api.security;

import com.jarspeed.api.user.User;
import com.jarspeed.api.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Base64;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Token service test.
 */
class TokenServiceTest {

    /**
     * The User repository.
     */
    @Mock
    private UserRepository userRepository;

    /**
     * The Token service.
     */
    @InjectMocks
    private TokenService tokenService;

    /**
     * The User id.
     */
    private Integer userId;
    /**
     * The Token.
     */
    private String token;
    /**
     * The Mock user.
     */
    private User mockUser;

    /**
     * The constant TOKEN_EXPIRY_TIME.
     */
    private static final long TOKEN_EXPIRY_TIME = 3600000 * 5;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userId = 1;
        token = tokenService.generateToken(userId);
        mockUser = new User();
        mockUser.setId(userId);

        when(userRepository.findByToken(anyString())).thenReturn(mockUser);
    }

    /**
     * Test generate token.
     */
    @Test
    void testGenerateToken() {
        assertNotNull(token, "Token should not be null");
    }

    /**
     * Test validate token.
     */
    @Test
    void testValidateToken() {
        assertTrue(tokenService.validateToken(token), "Token should be valid immediately after being generated");
    }

    /**
     * Test get user id from token when user found.
     */
    @Test
    void testGetUserIdFromTokenWhenUserFound() {
        // Configuration du comportement mocké pour retourner un utilisateur spécifique
        when(userRepository.findByToken(anyString())).thenReturn(mockUser);

        Integer retrievedUserId = tokenService.getUserIdFromToken(token);
        assertNotNull(retrievedUserId, "User ID should be retrieved from token");
        assertEquals(userId, retrievedUserId, "Retrieved user ID should match the original one");
    }

    /**
     * Test get user id from token when user not found.
     */
    @Test
    void testGetUserIdFromTokenWhenUserNotFound() {
        // Configurer le mock pour retourner null lorsqu'aucun utilisateur correspondant au token n'est trouvé
        when(userRepository.findByToken(anyString())).thenReturn(null);

        Integer retrievedUserId = tokenService.getUserIdFromToken("invalidToken");
        assertNull(retrievedUserId, "Should return null when no user is associated with the token");
    }

    /**
     * Test validate token with invalid format.
     */
    @Test
    void testValidateTokenWithInvalidFormat() {
        String invalidToken = "notABase64Token";
        assertFalse(tokenService.validateToken(invalidToken), "Token with invalid format should not be valid");
    }

    /**
     * Test validate token with invalid structure.
     */
    @Test
    void testValidateTokenWithInvalidStructure() {
        String invalidStructureToken = Base64.getEncoder().encodeToString("invalidStructure".getBytes());
        assertFalse(tokenService.validateToken(invalidStructureToken), "Token with invalid structure should not be valid");
    }

    /**
     * Test validate token expiration.
     */
    @Test
    void testValidateTokenExpiration() {
        // Générer un token avec un timestamp expiré
        long expiredTimestamp = System.currentTimeMillis() - (TOKEN_EXPIRY_TIME + 1000); // 1000 ms après l'expiration
        String tokenData = userId + ":" + expiredTimestamp + ":" + UUID.randomUUID().toString();
        String expiredToken = Base64.getEncoder().encodeToString(tokenData.getBytes());

        // Vérifier que le token est considéré comme expiré
        assertFalse(tokenService.validateToken(expiredToken), "Expired token should not be valid");
    }


}
