package com.jarspeed.api.route;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository interface for Route entity.
 * This interface extends the MongoRepository interface, providing CRUD operations and
 * MongoDB-specific functionality for Route objects.
 *
 * By extending MongoRepository, this interface automatically inherits methods for
 * saving, deleting, and finding Route entities.
 *
 * Custom query methods specific to Route entity can be defined in this interface if needed.
 */
public interface RouteRepository extends MongoRepository<Route, String> {
    // Custom query methods can be defined here
}
