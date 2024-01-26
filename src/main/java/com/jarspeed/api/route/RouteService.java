package com.jarspeed.api.route;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;



/**
 * Service for managing routes.
 * This service contains business logic related to routes and interacts with the RouteRepository.
 */
@Service
public class RouteService {

    @Autowired
    private RouteRepository routeRepository; // Injects an instance of RouteRepository.

    /**
     * Retrieves all routes.
     * This method calls the repository to fetch all routes stored in the database.
     *
     * @return a list of all routes.
     */
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    /**
     * Retrieves a route by its ID.
     * If the route is found, it is returned, otherwise, null is returned.
     *
     * @param id The ID of the route.
     * @return the route if found, or null.
     */
    public Route getRouteById(String id) {
        Optional<Route> route = routeRepository.findById(id);
        return route.orElse(null);
    }

    /**
     * Creates a new route.
     * This method saves the provided route object in the database.
     *
     * @param route The route to be created.
     * @return the created route.
     */
    public Route createRoute(Route route) {
        return routeRepository.save(route);
    }

    /**
     * Updates an existing route.
     * If the route exists, it is updated with the provided details and saved.
     *
     * @param id The ID of the route to be updated.
     * @param routeDetails The new details for the route.
     * @return the updated route, or null if the route doesn't exist.
     */
    public Route updateRoute(String id, Route routeDetails) {
        Route route = routeRepository.findById(id).orElse(null);
        if (route != null) {
            // Update logic here
            route.setDate(routeDetails.getDate());
            route.setStartPoint(routeDetails.getStartPoint());
            route.setEndPoint(routeDetails.getEndPoint());
            route.setPath(routeDetails.getPath());
            route.setPointsOfInterest(routeDetails.getPointsOfInterest());
            route.setTitle(routeDetails.getTitle());
            route.setDescription(routeDetails.getDescription());

            return routeRepository.save(route);
        }
        return null;
    }

    /**
     * Deletes a route.
     * This method removes the route with the specified ID from the database.
     *
     * @param id The ID of the route to be deleted.
     */
    public void deleteRoute(String id) {
        routeRepository.deleteById(id);
    }

}
