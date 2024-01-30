package com.jarspeed.api.user;

import com.jarspeed.api.gender.Gender;
import com.jarspeed.api.gender.GenderController;
import com.jarspeed.api.gender.GenderRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.jarspeed.api.security.TokenService;
import com.jarspeed.api.security.RefreshTokenService;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    private final Gender HOMME = new Gender(1, "Homme");
    private final List<User> USERS = List.of(new User(1, "McGregor", "Conor",
     "conor.mcgregor@gmail.com", new Date(1985,9,12), HOMME, 71.0, "password"), new User(2,
            "Saint Denis", "Benoît",
            "benoit.saintdenis@gmail.com", new Date(2000,6,16), HOMME, 70.3, "password"));

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserController userController;

    @Mock
    private TokenService tokenService;

    @Test
    void getAllWithValidToken() {
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        when(mockRequest.getHeader("Authorization")).thenReturn("Bearer validToken");
        when(tokenService.validateToken("validToken")).thenReturn(true);
        when(userRepository.findAll()).thenReturn(USERS);

        ResponseEntity<?> response = userController.getAll(mockRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(USERS, response.getBody());
    }

    @Test
    void getAllWithInvalidToken() {
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        when(mockRequest.getHeader("Authorization")).thenReturn("Bearer invalidToken");
        when(tokenService.validateToken("invalidToken")).thenReturn(false);

        ResponseEntity<?> response = userController.getAll(mockRequest);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    void findByIdWithValidToken() {
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        when(mockRequest.getHeader("Authorization")).thenReturn("Bearer validToken");
        when(tokenService.validateToken("validToken")).thenReturn(true);
        when(userRepository.findUserById(1)).thenReturn(USERS.get(0)); // Exemple avec l'ID 1

        ResponseEntity<?> response = userController.findById(1, mockRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(USERS.get(0), response.getBody());
    }

    @Test
    void findByIdWithInvalidToken() {
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        when(mockRequest.getHeader("Authorization")).thenReturn("Bearer invalidToken");
        when(tokenService.validateToken("invalidToken")).thenReturn(false);

        ResponseEntity<?> response = userController.findById(1, mockRequest);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }


    @Test
    void mergeUserEmailChanged() {
        // Utilisateur existant dans la base de données
        User existingUser = new User(1, "McGregor", "Conor",
                "conor.mcgregor@gmail.com", new Date(1985,9,12), HOMME, 71.0, "oldPassword");

        // Nouvelles données de l'utilisateur à fusionner
        User newUser = new User(1,"Saint Denis", "Benoît",
                "benoit.saintdenis@gmail.com", new Date(2000,6,16), HOMME, 70.3, "newPassword");

        // Simuler le comportement du userRepository
        when(userRepository.findUserById(anyInt())).thenReturn(existingUser);
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArguments()[0]);

        // Appeler la méthode merge
        User mergedUser = userController.merge(newUser);

        // Vérifier que userRepository.save est appelé avec le bon utilisateur
        verify(userRepository).save(any(User.class));

        // Assertions pour vérifier si les champs sont correctement fusionnés
        assertEquals(newUser.getLastname(), mergedUser.getLastname());
        assertEquals(newUser.getFirstname(), mergedUser.getFirstname());
        assertEquals(newUser.getEmail(), mergedUser.getEmail());
        assertEquals(newUser.getBirthdate(), mergedUser.getBirthdate());
        assertEquals(newUser.getGender(), mergedUser.getGender());
        assertEquals(newUser.getWeight(), mergedUser.getWeight());
        assertEquals(newUser.getPassword(), mergedUser.getPassword());
    }
}