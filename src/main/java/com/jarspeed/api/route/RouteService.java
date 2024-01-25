package com.jarspeed.api.route;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;
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
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    public Route getRouteById(String id) {
        Optional<Route> route = routeRepository.findById(id);
        return route.orElse(null);
    }

    public Route createRoute(Route route) {
        return routeRepository.save(route);
    }

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

    public void deleteRoute(String id) {
        routeRepository.deleteById(id);
    }

}
