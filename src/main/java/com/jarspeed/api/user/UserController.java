package com.jarspeed.api.user;

import com.jarspeed.api.security.RefreshTokenService;
import com.jarspeed.api.security.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam String email, @RequestParam String password) {
        User user = userRepository.findUserByEmailAndPassword(email, password);
        if (user != null) {
            String token = tokenService.generateToken(user.getId());
            String refreshToken = refreshTokenService.generateRefreshToken(user.getId());
            return ResponseEntity.ok(Map.of("token", token, "refreshToken", refreshToken));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<?> refreshToken(@RequestParam String refreshToken) {
        if (refreshTokenService.validateRefreshToken(refreshToken)) {
            Integer userId = refreshTokenService.getUserIdFromRefreshToken(refreshToken);
            String newToken = tokenService.generateToken(userId);
            return ResponseEntity.ok(Map.of("token", newToken));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
        }
    }

    /**
     * Recovery all users.
     *
     * @return all users in table
     */
    @GetMapping("/")
    public ResponseEntity<?> getAll(HttpServletRequest request) {
        String token = extractToken(request);
        if (token == null || !tokenService.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");
        }

        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    /**
     * Find user by id.
     *
     * @param pId id for search
     * @return user of search with criteria (user.id = pId)
     */
    @GetMapping("/findById")
    public ResponseEntity<?> findById(@RequestParam Integer pId,
                               HttpServletRequest request) {
        String token = extractToken(request);
        if (token == null || !tokenService.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");
        }

        User user = userRepository.findUserById(pId);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationRequest registrationRequest) {
        // Vérifier si l'email existe déjà
        if (userRepository.existsByEmail(registrationRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already in use");
        }

        // Créer un nouvel utilisateur avec des valeurs par défaut pour les champs non renseignés
        User newUser = new User();
        newUser.setLastname(registrationRequest.getLastname());
        newUser.setFirstname(registrationRequest.getFirstname());
        newUser.setEmail(registrationRequest.getEmail());
        newUser.setPassword(hashPassword(registrationRequest.getPassword()));
        newUser.setAge(null); // Valeur par défaut pour l'âge
        newUser.setWeight(null); // Valeur par défaut pour le poids
        newUser.setGender(null); // Vous pouvez également définir une valeur par défaut ou laisser null si autorisé

        // Enregistrer l'utilisateur dans la base de données
        userRepository.save(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }


    private String hashPassword(String password) {
        // Utiliser BCrypt ou un autre algorithme de hachage pour sécuriser le mot de passe
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
        if (pUser.getAge() != null) {
            if (user.getAge() != null) {
                // The new age is equals to the old
                if (!pUser.getAge().equals(user.getAge())) {
                    user.setAge(pUser.getAge());
                }
            } else {
                // Age has never been initialised
                user.setAge(pUser.getAge());
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

    // Utilitaire pour extraire le token du header de la requête
    private String extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
