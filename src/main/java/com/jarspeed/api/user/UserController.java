package com.jarspeed.api.user;

import com.jarspeed.api.gender.Gender;
import com.jarspeed.api.gender.GenderRepository;
import com.jarspeed.api.security.RefreshTokenService;
import com.jarspeed.api.security.TokenService;
import com.jarspeed.api.security.TokenUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Map;

/**
 * REST controller for user management.
 * <p>
 * This controller handles HTTP requests for operations on.
 * users, such as retrieving, creating, updating and deleting
 * users.
 * </p>
 */
@RestController
@RequestMapping("/users")
public class UserController {

    /**
     * The Default weight.
     */
    public static final double DEFAULT_WEIGHT = 70.0;
    /**
     * The Empty gender.
     */
    private static final int EMPTY_GENDER = 3;

    /**
     * The Gender repository.
     */
    @Autowired
    private GenderRepository genderRepository;

    /**
     * Service to manage user-related operations.
     * The Spring framework automatically injects a UserService instance.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * The Token service.
     */
    @Autowired
    private TokenService tokenService;

    /**
     * The Refresh token service.
     */
    @Autowired
    private RefreshTokenService refreshTokenService;

    /**
     * The constant logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(
            UserController.class);

    /**
     * Login user response entity.
     *
     * @param email    the email
     * @param password the password
     * @return the response entity
     */
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(final @RequestParam String email,
                                       final @RequestParam String password) {
        User user = userRepository.findUserByEmailAndPassword(email, password);
        if (user != null) {
            String token = tokenService.generateToken(user.getId());
            user.setToken(token); // Stockez le token dans l'utilisateur
            userRepository.save(user); // Sauvegardez utilisateur avec le token
            String refreshToken =
                    refreshTokenService.generateRefreshToken(user.getId());
            return ResponseEntity.ok(Map.of("token", token,
                    "refreshToken", refreshToken, "weight", user.getWeight()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid credentials");
        }
    }

    /**
     * Refresh token response entity.
     *
     * @param refreshToken the refresh token
     * @return the response entity
     */
    @PostMapping("/refreshToken")
    public ResponseEntity<?> refreshToken(
            final @RequestParam String refreshToken) {
        if (refreshTokenService.validateRefreshToken(refreshToken)) {
            Integer userId = refreshTokenService
                    .getUserIdFromRefreshToken(refreshToken);
            String newToken = tokenService.generateToken(userId);
            return ResponseEntity.ok(Map.of("token", newToken));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid refresh token");
        }
    }

    /**
     * Recovery all users.
     *
     * @param request the request
     * @return all users in table
     */
    @GetMapping("/")
    public ResponseEntity<?> getAll(final HttpServletRequest request) {
        String token = TokenUtils.extractToken(request);
        if (token == null || !tokenService.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Unauthorized access");
        }

        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    /**
     * Find user by id.
     *
     * @param pId     id for search
     * @param request the request
     * @return user of search with criteria (user.id = pId)
     */
    @GetMapping("/findById")
    public ResponseEntity<?> findById(final @RequestParam Integer pId,
                                      final HttpServletRequest request) {
        String token = TokenUtils.extractToken(request);
        if (token == null || !tokenService.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Unauthorized access");
        }

        User user = userRepository.findUserById(pId);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found");
        }
    }

    /**
     * Register user response entity.
     *
     * @param registrationRequest the registration request
     * @return the response entity
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(
            final @RequestBody UserRegistrationRequest registrationRequest) {
        // Vérifier si l'email existe déjà
        if (userRepository.existsByEmail(registrationRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Email already in use");
        }

        Gender genDef = genderRepository.findGenderById(EMPTY_GENDER);

        // Créer un nouvel utilisateur avec des valeurs par défaut pour
        // les champs non renseignés
        User newUser = new User();
        newUser.setLastname(registrationRequest.getLastname());
        newUser.setFirstname(registrationRequest.getFirstname());
        newUser.setEmail(registrationRequest.getEmail());
        newUser.setPassword(hashPassword(registrationRequest.getPassword()));
        newUser.setBirthdate(null); // Valeur par défaut pour l'âge
        newUser.setWeight(DEFAULT_WEIGHT); // Valeur par défaut pour le poids
        newUser.setGender(genDef); // Vous pouvez également définir une valeur
                                // par défaut ou laisser null si autorisé

        // Enregistrer l'utilisateur dans la base de données
        userRepository.save(newUser);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("User registered successfully");
    }


    /**
     * Hash password string.
     *
     * @param password the password
     * @return the string
     */
    private String hashPassword(final String password) {
        // Utiliser BCrypt ou un autre algorithme de hachage
        // pour sécuriser le mot de passe
        return password; // Remplacer par la logique de hachage réelle
    }


    /**
     * Update user response entity.
     *
     * @param request       the request
     * @param updateRequest the update request
     * @return the response entity
     */
    @PutMapping("/update")
    public ResponseEntity<?> updateUser(final HttpServletRequest request,
                                        final @RequestBody UserUpdateRequest
                                                updateRequest) {
        String token = TokenUtils.extractToken(request);
        if (token == null) {
            LOGGER.error("No token provided");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("No token provided");
        }

        LOGGER.info("Update request received with token: {}", token);

        Integer userId = tokenService.getUserIdFromToken(token);
        if (userId != null) {
            LOGGER.info("Token corresponds to user ID: {}", userId);
            User user = userRepository.findUserById(userId);
            if (user != null) {
                user.updateUserInfos(updateRequest);
                userRepository.save(user);
                LOGGER.info("User with ID: {} updated successfully", userId);
                return ResponseEntity.ok("User updated successfully");
            } else {
                LOGGER.error("User with ID: {} not found", userId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("User not found");
            }
        } else {
            LOGGER.error("Invalid or expired token");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid token");
        }
    }

    /**
     * Delete account response entity.
     *
     * @param request the request
     * @return the response entity
     */
    @DeleteMapping("/deleteAccount")
    public ResponseEntity<?> deleteAccount(final HttpServletRequest request) {
        String token = TokenUtils.extractToken(request);
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Unauthorized: No token provided");
        }

        Integer userId = tokenService.getUserIdFromToken(token);
        if (userId != null) {
            userRepository.deleteById(userId);
            return ResponseEntity.ok("Account deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Unauthorized: Invalid token");
        }
    }

    /**
     * Gets user profile.
     *
     * @param request the request
     * @return the user profile
     */
    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile(final HttpServletRequest request) {
        String token = TokenUtils.extractToken(request);
        if (token == null || !tokenService.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Unauthorized access");
        }

        Integer userId = tokenService.getUserIdFromToken(token);
        User user = userRepository.findUserById(userId);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found");
        }
    }

}
