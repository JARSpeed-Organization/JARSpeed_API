package com.jarspeed.api.user;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for the User entity.
 * <p>
 * This interface extends JpaRepository, providing CRUD methods for the User
 * entity.
 * the User entity. It can also be used to define custom query
 * query methods.
 * </p>
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Find user by id.
     *
     * @param pId Id of user
     * @return The user
     */
    User findUserById(Integer pId);

    /**
     * Find user by email and password user.
     *
     * @param email    the email
     * @param password the password
     * @return the user
     */
    User findUserByEmailAndPassword(String email, String password);

    /**
     * Exists by email boolean.
     *
     * @param email the email
     * @return the boolean
     */
    boolean existsByEmail(String email);


}
