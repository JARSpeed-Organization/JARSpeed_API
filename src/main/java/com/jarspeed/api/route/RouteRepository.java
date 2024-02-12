package com.jarspeed.api.route;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Repository interface for Route entity.
 * This interface extends the MongoRepository interface, providing CRUD
 * operations and MongoDB-specific functionality for Route objects.
 * <p>
 * By extending MongoRepository, this interface automatically inherits methods
 * for saving, deleting, and finding Route entities.
 * <p>
 * Custom query methods specific to Route entity can be defined in this
 * interface if needed.
 */
public interface RouteRepository extends MongoRepository<Route, String> {
    // Custom query methods can be defined here

    /**
     * Handles the GET request to retrieve all routes by user id.
     *
     * @param pUserId the user id
     * @return a list of all routes by user id.
     */
    List<Route> findAllByUserId(String pUserId);
}
