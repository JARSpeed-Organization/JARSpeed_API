package com.jarspeed.api.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller class for managing routes.
 * <p>
 * This class handles HTTP requests and responses for operations related to
 * routes.
 * </p>
 */
@RestController
@RequestMapping("/routes")
public class RouteController {
    // Dependency injection for the Route service
    /**
     * Service to manage route operations.
     * The Spring framework automatically injects an instance of
     * RouteService.
     */
    @Autowired
    private RouteService routeService;

    /**
     * Retrieves and returns a list of all routes.
     * This method handles GET requests to obtain a list of all
     * routes.
     * It uses the RouteService to query the database and
     * Returns the list of routes.
     *
     * @return The list of routes available in the database.
     */
    @GetMapping
    public List<Route> getAllRoute() {
        return routeService.findAll();
    }
}
