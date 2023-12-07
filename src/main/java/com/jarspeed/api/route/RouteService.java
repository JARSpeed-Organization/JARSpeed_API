package com.jarspeed.api.route;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for sports course management.
 * <p>
 * This service contains the business logic linked to courses and interacts
 * with the ParcoursRepository.
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
     * courses in the database.
     * </p>
     */
    private RouteRepository courseRepository;

    /**
     * Retrieves the list of all courses.
     * <p>
     * This method calls the repository to retrieve all courses
     * stored in the database.
     * </p>
     *
     * @return a list of all courses.
     */
    public List<Route> findAll() {
        return courseRepository.findAll();
    }

}
