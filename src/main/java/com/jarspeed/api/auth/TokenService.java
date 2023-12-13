package com.jarspeed.api.auth;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

/**
 * Service for token management.
 */
@Service
public class TokenService {

    /**
     * Encoder for token generation.
     */
    private final JwtEncoder encoder;

    /**
     * Constructor for initialise encoder.
     * @param pEncoder Encoder
     */
    public TokenService(final JwtEncoder pEncoder) {
        this.encoder = pEncoder;
    }

    /**
     * Generate a new token for authentication. This token is valid for 1 hour.
     * @param pAuthentication Generate a token for authentication
     * @return A new token for authentication
     */
    public String generateToken(final Authentication pAuthentication) {
        Instant now = Instant.now();
        String scope = pAuthentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .filter(authority -> !authority.startsWith("ROLE"))
                .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .subject(pAuthentication.getName())
                .claim("scope", scope)
                .build();
        var encoderParameters = JwtEncoderParameters
                .from(JwsHeader.with(MacAlgorithm.HS256).build(), claims);
        return this.encoder.encode(encoderParameters).getTokenValue();
    }
}
