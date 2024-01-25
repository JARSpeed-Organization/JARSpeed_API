package com.jarspeed.api.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controller for handling HTTP requests related to routes.
 * This class is marked as a REST Controller, meaning it will handle web requests.
 */
@RestController
@RequestMapping("/routes") // Maps HTTP requests to handler methods of MVC and REST controllers.
public class RouteController {

    @Autowired
    private RouteService routeService; // Injects an instance of RouteService.

    /**
     * Handles the GET request to retrieve all routes.
     * @return a list of all routes.
     */
    @GetMapping
    public List<Route> getAllRoutes() {
        return routeService.getAllRoutes();
    }

    /**
     * Handles the GET request to retrieve a specific route by its ID.
     * @param id The ID of the route.
     * @return the route associated with the given ID.
     */
    @GetMapping("/{id}")
    public Route getRouteById(@PathVariable String id) {
        return routeService.getRouteById(id);
    }

    /**
     * Handles the POST request to create a new route.
     * @param route The route object to be created.
     * @return the created route.
     */
    @PostMapping
    public Route createRoute(@RequestBody Route route) {
        return routeService.createRoute(route);
    }

    /**
     * Handles the PUT request to update an existing route.
     * @param id The ID of the route to be updated.
     * @param route The route object with updated information.
     * @return the updated route.
     */
    @PutMapping("/{id}")
    public Route updateRoute(@PathVariable String id, @RequestBody Route route) {
        return routeService.updateRoute(id, route);
    }

    /**
     * Handles the DELETE request to remove a route.
     * @param id The ID of the route to be deleted.
     */
    @DeleteMapping("/{id}")
    public void deleteRoute(@PathVariable String id) {
        routeService.deleteRoute(id);
    }
}
