package com.jarspeed.api.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller class for managing courses.
 * <p>
 * This class handles HTTP requests and responses for operations related to
 * courses.
 * </p>
 */
@RestController
@RequestMapping("/parcours")
public class RouteController {
    // Dependency injection for the Parcours service
    /**
     * Service to manage route operations.
     * The Spring framework automatically injects an instance of
     * ParcoursService.
     */
    @Autowired
    private RouteService courseService;

    /**
     * Retrieves and returns a list of all routes.
     * This method handles GET requests to obtain a list of all
     * courses.
     * It uses the ParcoursService to query the database and
     * Returns the list of courses.
     *
     * @return The list of routes available in the database.
     */
    @GetMapping
    public List<Route> getAllParcours() {
        return courseService.findAll();
    }
}
