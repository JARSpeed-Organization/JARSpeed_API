package com.jarspeed.api.config;


import com.nimbusds.jose.jwk.source.ImmutableSecret;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Classe de configuration pour la sécurité de l'application.
 * <p>
 * Cette classe configure les aspects de sécurité de l'application,
 * comme l'authentification et l'autorisation.
 * </p>
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * JWT Key.
     */
    @Value("${jwt.key}")
    private String jwtKey;

    /**
     * Create a user. A MODIFIER AVEC BDD
     * @return Memory user details manager
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.withUsername("USERNAME")
                        .password("{noop}PASSWORD")
                        .authorities("READ", "ROLE_USER")
                        .build()
        );
    }

    /**
     * This method is responsible for all the security
     * (protecting the application URLs, validating submitted
     * username and passwords) within application.
     * @param http HttpSecurity
     * @return Spring Security filter chain
     * @throws Exception All exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http)
            throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/token").hasRole("USER")
                        .anyRequest().hasAuthority("SCOPE_READ")
                )
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy
                                .STATELESS))
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .httpBasic(withDefaults())
                .build();
    }

    /**
     * JWT Encoder with the jwtKey.
     * @return JWT Encoder with the jwtKey
     */
    @Bean
    JwtEncoder jwtEncoder() {
        return new NimbusJwtEncoder(new ImmutableSecret<>(jwtKey.getBytes()));
    }

    /**
     * JWT Decoder with the jwtKey.
     * @return JWT Decoder with the jwtKey
     */
    @Bean
    public JwtDecoder jwtDecoder() {
        byte[] bytes = jwtKey.getBytes();
        SecretKeySpec originalKey =
                new SecretKeySpec(bytes, 0, bytes.length, "RSA");
        return NimbusJwtDecoder
                .withSecretKey(originalKey)
                .macAlgorithm(MacAlgorithm.HS256)
                .build();
    }
}
