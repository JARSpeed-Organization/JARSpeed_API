package com.jarspeed.api.route;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository interface for the Route entity.
 * <p>
 * This interface extends JpaRepository, providing CRUD methods for the
 * the Route entity. It can also be used to define
 * custom query methods.
 * </p>
 */
public interface RouteRepository extends MongoRepository<Route, String> {
    // Custom query methods can be defined here
}

