package com.jarspeed.api.route;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for sports route management.
 * <p>
 * This service contains the business logic linked to courses and interacts
 * with the RouteRepository.
 * </p>
 */
@Service
public class RouteService {

    /**
     * Reference to the repository of
     * {@link RouteRepository}
     * for accessing and manipulating course data.
     * <p>
     * This repository is used to perform CRUD operations on the
     * routes in the database.
     * </p>
     */
    private RouteRepository routeRepository;

    /**
     * Retrieves the list of all routes.
     * <p>
     * This method calls the repository to retrieve all routes
     * stored in the database.
     * </p>
     *
     * @return a list of all routes.
     */
    public List<Route> findAll() {
        return routeRepository.findAll();
    }

}
