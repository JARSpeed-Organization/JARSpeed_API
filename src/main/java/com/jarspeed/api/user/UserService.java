package com.jarspeed.api.user;

import org.springframework.stereotype.Service;

/**
 * Service for user management.
 * <p>
 * This service contains the business logic linked to users and interacts
 * with the UserRepository.
 * </p>
 */
@Service
public class UserService {
    /**
     * Reference to the repository of
     * {@link UserRepository}
     * for accessing and manipulating user data.
     * <p>
     * This repository is used to perform CRUD operations on * users in the
     * database.
     * users in the database.
     * </p>
     */
    private UserRepository userRepository;

}
