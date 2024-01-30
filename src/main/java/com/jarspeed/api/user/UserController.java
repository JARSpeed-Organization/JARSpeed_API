package com.jarspeed.api.user;

import com.jarspeed.api.security.RefreshTokenService;
import com.jarspeed.api.security.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
            String refreshToken =
                    refreshTokenService.generateRefreshToken(user.getId());
            return ResponseEntity.ok(Map.of("token", token, "refreshToken",
                    refreshToken));
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
        String token = extractToken(request);
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
        String token = extractToken(request);
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

        // Créer un nouvel utilisateur avec des valeurs par défaut pour
        // les champs non renseignés
        User newUser = new User();
        newUser.setLastname(registrationRequest.getLastname());
        newUser.setFirstname(registrationRequest.getFirstname());
        newUser.setEmail(registrationRequest.getEmail());
        newUser.setPassword(hashPassword(registrationRequest.getPassword()));
        newUser.setBirthdate(null); // Valeur par défaut pour l'âge
        newUser.setWeight(null); // Valeur par défaut pour le poids
        newUser.setGender(null); // Vous pouvez également définir une valeur
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
     * Merge the new user information with the old.
     * If an information is null or if the new information is equal to the old
     * information, no changes are made to this information.
     *
     * @param pUser new user
     * @return new user, after merge
     */
    @PutMapping("/merge")
    public User merge(@RequestBody final User pUser) {
        User user = userRepository.findUserById(pUser.getId());
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        if (pUser.getEmail() != null) {
            if (user.getEmail() != null) {
                // The new email is equals to the old
                if (!pUser.getEmail().equals(user.getEmail())) {
                    user.setEmail(pUser.getEmail());
                }
            } else {
                // Email has never been initialised
                user.setEmail(pUser.getEmail());
            }
        }
        if (pUser.getLastname() != null) {
            if (user.getLastname() != null) {
                // The new lastname is equals to the old
                if (!pUser.getLastname().equals(user.getLastname())) {
                    user.setLastname(pUser.getLastname());
                }
            } else {
                // Lastname has never been initialised
                user.setLastname(pUser.getLastname());
            }
        }
        if (pUser.getFirstname() != null) {
            if (user.getFirstname() != null) {
                // The new firstname is equals to the old
                if (!pUser.getFirstname().equals(user.getFirstname())) {
                    user.setFirstname(pUser.getFirstname());
                }
            } else {
                // Firstname has never been initialised
                user.setFirstname(pUser.getFirstname());
            }
        }
        if (pUser.getBirthdate() != null) {
            if (user.getBirthdate() != null) {
                // The new age is equals to the old
                if (!pUser.getBirthdate().equals(user.getBirthdate())) {
                    user.setBirthdate(pUser.getBirthdate());
                }
            } else {
                // Age has never been initialised
                user.setBirthdate(pUser.getBirthdate());
            }
        }
        if (pUser.getWeight() != null) {
            if (user.getWeight() != null) {
                // The new weight is equals to the old
                if (!pUser.getWeight().equals(user.getWeight())) {
                    user.setWeight(pUser.getWeight());
                }
            } else {
                // Weight has never been initialised
                user.setWeight(pUser.getWeight());
            }
        }
        if (pUser.getGender() != null) {
            if (user.getGender() != null) {
                // The new gender is equals to the old
                if (!pUser.getGender().equals(user.getGender())) {
                    user.setGender(pUser.getGender());
                }
            } else {
                // Gender has never been initialised
                user.setGender(pUser.getGender());
            }
        }
        if (pUser.getPassword() != null) {
            if (user.getPassword() != null) {
                // The new password is equals to the old
                if (!pUser.getPassword().equals(user.getPassword())) {
                    user.setPassword(pUser.getPassword());
                }
            } else {
                // Password has never been initialised
                user.setPassword(pUser.getPassword());
            }
        }
        return userRepository.save(user);
    }


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
// Utilitaire pour extraire le token du header de la requête
    private String extractToken(final HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(BEGIN_INDEX);
        }
        return null;
    }
}
