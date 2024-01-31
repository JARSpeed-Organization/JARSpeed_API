package com.jarspeed.api.user;

import com.jarspeed.api.gender.Gender;
import com.jarspeed.api.security.TokenService;
import com.jarspeed.api.security.RefreshTokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;


@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private TokenService tokenService;

    @Mock
    private RefreshTokenService refreshTokenService;

    @InjectMocks
    private UserController userController;

    private User testUser;
    private UserRegistrationRequest testRegistrationRequest;

    private User existingUser;
    private User updatedUser;
    @BeforeEach
    void setUp() {
        testUser = new User(1, "Test", "User", "test@example.com", new Date(), null, 60.0, "password");
        testRegistrationRequest = new UserRegistrationRequest("Test", "User", "test@example.com", "password");
        existingUser = new User(1, "ExistingLastName", "ExistingFirstName",
                "existing@example.com", new Date(), new Gender(1, "Homme"), 70.0
                , "existingPassword");
        updatedUser = new User(1, "UpdatedLastName", "UpdatedFirstName",
                "updated@example.com", new Date(), new Gender(2, "Femme"), 80.0,
                "updatedPassword");

    }

    @Test
    void loginUserWithValidCredentials() {
        when(userRepository.findUserByEmailAndPassword("test@example.com", "password")).thenReturn(testUser);
        when(tokenService.generateToken(testUser.getId())).thenReturn("validToken");
        when(refreshTokenService.generateRefreshToken(testUser.getId())).thenReturn("validRefreshToken");

        ResponseEntity<?> response = userController.loginUser("test@example.com", "password");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().toString().contains("validToken"));
        assertTrue(response.getBody().toString().contains("validRefreshToken"));
    }

    @Test
    void loginUserWithInvalidCredentials() {
        when(userRepository.findUserByEmailAndPassword("invalid@example.com", "password")).thenReturn(null);

        ResponseEntity<?> response = userController.loginUser("invalid@example.com", "password");

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Invalid credentials", response.getBody());
    }

    @Test
    void refreshTokenWithValidToken() {
        when(refreshTokenService.validateRefreshToken("validRefreshToken")).thenReturn(true);
        when(refreshTokenService.getUserIdFromRefreshToken("validRefreshToken")).thenReturn(1);
        when(tokenService.generateToken(1)).thenReturn("newToken");

        ResponseEntity<?> response = userController.refreshToken("validRefreshToken");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().toString().contains("newToken"));
    }

    @Test
    void refreshTokenWithInvalidToken() {
        when(refreshTokenService.validateRefreshToken("invalidRefreshToken")).thenReturn(false);

        ResponseEntity<?> response = userController.refreshToken("invalidRefreshToken");

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Invalid refresh token", response.getBody());
    }

    @Test
    void getAllWithValidToken() {
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        when(mockRequest.getHeader("Authorization")).thenReturn("Bearer validToken");
        when(tokenService.validateToken("validToken")).thenReturn(true);
        when(userRepository.findAll()).thenReturn(List.of(testUser));

        ResponseEntity<?> response = userController.getAll(mockRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(List.of(testUser), response.getBody());
    }

    @Test
    void getAllWithInvalidToken() {
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        when(mockRequest.getHeader("Authorization")).thenReturn("Bearer invalidToken");
        when(tokenService.validateToken("invalidToken")).thenReturn(false);

        ResponseEntity<?> response = userController.getAll(mockRequest);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Unauthorized access", response.getBody());
    }

    @Test
    void findByIdWithValidTokenAndUserExists() {
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        when(mockRequest.getHeader("Authorization")).thenReturn("Bearer validToken");
        when(tokenService.validateToken("validToken")).thenReturn(true);
        when(userRepository.findUserById(1)).thenReturn(testUser);

        ResponseEntity<?> response = userController.findById(1, mockRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testUser, response.getBody());
    }

    @Test
    void findByIdWithValidTokenAndUserNotExists() {
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        when(mockRequest.getHeader("Authorization")).thenReturn("Bearer validToken");
        when(tokenService.validateToken("validToken")).thenReturn(true);
        when(userRepository.findUserById(1)).thenReturn(null);

        ResponseEntity<?> response = userController.findById(1, mockRequest);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User not found", response.getBody());
    }

    @Test
    void findByIdWithInvalidToken() {
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        when(mockRequest.getHeader("Authorization")).thenReturn("Bearer invalidToken");
        when(tokenService.validateToken("invalidToken")).thenReturn(false);

        ResponseEntity<?> response = userController.findById(1, mockRequest);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Unauthorized access", response.getBody());
    }

    @Test
    void registerUserSuccessfully() {
        when(userRepository.existsByEmail("test@example.com")).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(testUser);

        ResponseEntity<?> response = userController.registerUser(testRegistrationRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("User registered successfully", response.getBody());
    }

    @Test
    void registerUserWithEmailAlreadyExists() {
        when(userRepository.existsByEmail("existing@example.com")).thenReturn(true);

        ResponseEntity<?> response = userController.registerUser(new UserRegistrationRequest("Existing", "User", "existing@example.com", "password"));

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Email already in use", response.getBody());
    }

}
