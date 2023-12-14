package com.jarspeed.api.user;

import com.jarspeed.api.route.Route;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

/**
 * Service for user management.
 * <p>
 * This service contains the business logic linked to users and interacts
 * with the UserRepository.
 * </p>
 */

@Service
public class UserService {
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }
    // Autres méthodes métier si nécessaire
}

