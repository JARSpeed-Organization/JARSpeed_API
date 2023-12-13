package com.jarspeed.api.auth;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Authentification controller
 * <p>
 *     This controller is used in first connection. He give a token.
 * </p>
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    /**
     * Token service.
     */
    private final TokenService tokenService;

    /**
     * Constructor for initialise tokenService.
     * @param pTokenService Token service
     */
    public AuthController(final TokenService pTokenService) {
        this.tokenService = pTokenService;
    }

    /**
     * Generate a new token.
     * @param pAuthentication Authentification information
     * @return A new Token for authentification
     */
    @PostMapping("/token")
    public String token(final Authentication pAuthentication) {
        return tokenService.generateToken(pAuthentication);
    }
}
