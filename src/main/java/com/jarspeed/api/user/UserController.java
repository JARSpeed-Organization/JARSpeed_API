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
    public ResponseEntity<?> merge(@RequestBody User pUser, HttpServletRequest request) {
        String token = extractToken(request);
        if (token == null || !tokenService.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");
        }

        User existingUser = userRepository.findUserById(pUser.getId());
        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        // Mise à jour de l'utilisateur existant avec les informations fournies
        updateUserInfo(existingUser, pUser);
        User updatedUser = userRepository.save(existingUser);
        return ResponseEntity.ok(updatedUser);
    }

    private void updateUserInfo(User existingUser, User newUser) {
        if (newUser.getEmail() != null && !newUser.getEmail().isEmpty()) {
            existingUser.setEmail(newUser.getEmail());
        }
        if (newUser.getLastname() != null && !newUser.getLastname().isEmpty()) {
            existingUser.setLastname(newUser.getLastname());
        }
        if (newUser.getFirstname() != null && !newUser.getFirstname().isEmpty()) {
            existingUser.setFirstname(newUser.getFirstname());
        }
        if (newUser.getAge() != null) {
            existingUser.setAge(newUser.getAge());
        }
        if (newUser.getWeight() != null) {
            existingUser.setWeight(newUser.getWeight());
        }
        if (newUser.getGender() != null) {
            existingUser.setGender(newUser.getGender());
        }
        // Note : Soyez prudent avec la mise à jour des mots de passe. Si vous souhaitez le permettre,
        // assurez-vous qu'ils sont correctement hachés avant de les stocker.
        if (newUser.getPassword() != null && !newUser.getPassword().isEmpty()) {
            // Ici, vous devriez hacher le mot de passe avant de le stocker
            existingUser.setPassword(newUser.getPassword());
        }
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
