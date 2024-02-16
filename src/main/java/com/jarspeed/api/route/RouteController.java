package com.jarspeed.api.route;

import com.jarspeed.api.security.TokenService;
import com.jarspeed.api.security.TokenUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

/**
 * Controller for handling HTTP requests related to routes.
 * This class is marked as a REST Controller, meaning it will handle web
 * requests.
 */
@RestController
@RequestMapping("/routes") // Maps HTTP requests to handler methods of MVC
// and REST controllers.
public class RouteController {

    /**
     * The Route service.
     */
    @Autowired
    private RouteService routeService; // Injects an instance of RouteService.

    /**
     * The Token service.
     */
    @Autowired
    private TokenService tokenService;

    /**
     * Handles the GET request to retrieve all routes by user id.
     *
     * @param pRequest the request
     * @return a list of all routes by user id.
     */
    @GetMapping
    public ResponseEntity<?> getAllRoutes(final HttpServletRequest pRequest) {
        String token = TokenUtils.extractToken(pRequest);
        if (token == null || !tokenService.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Unauthorized access");
        }
        Integer userId = tokenService.getUserIdFromToken(token);
        return ResponseEntity.ok(
                routeService.getAllRoutesByUserId(userId.toString()));
    }

    /**
     * Handles the GET request to retrieve a specific route by its ID.
     *
     * @param pRequest the p request
     * @param id       The ID of the route.
     * @return the route associated with the given ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getRouteById(final HttpServletRequest pRequest,
                               @PathVariable final String id) {
        String token = TokenUtils.extractToken(pRequest);
        if (token == null || !tokenService.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Unauthorized access");
        }
        return ResponseEntity.ok(routeService.getRouteById(id));
    }

    /**
     * Handles the POST request to create a new route.
     *
     * @param pRequest The request.
     * @param pRoute   The route object to be created.
     * @return the created route.
     */
    @PostMapping
    public ResponseEntity<?> createRoute(final HttpServletRequest pRequest,
                             @RequestBody final Route pRoute) {
        String token = TokenUtils.extractToken(pRequest);
        if (token == null || !tokenService.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Unauthorized access");
        }
        pRoute.setUserId(tokenService.getUserIdFromToken(token).toString());
        return ResponseEntity.ok(routeService.createRoute(pRoute));
    }

    /**
     * Handles the PUT request to update an existing route.
     *
     * @param pRequest the p request
     * @param id       The ID of the route to be updated.
     * @param route    The route object with updated information.
     * @return the updated route.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRoute(final HttpServletRequest pRequest,
                             @PathVariable final String id,
                             @RequestBody final Route route) {
        String token = TokenUtils.extractToken(pRequest);
        if (token == null || !tokenService.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Unauthorized access");
        }
        return ResponseEntity.ok(routeService.updateRoute(id, route));
    }

    /**
     * Handles the DELETE request to remove a route.
     *
     * @param pRequest the p request
     * @param id       The ID of the route to be deleted.
     * @return the response entity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRoute(final HttpServletRequest pRequest,
                            @PathVariable final String id) {
        String token = TokenUtils.extractToken(pRequest);
        if (token == null || !tokenService.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Unauthorized access");
        }
        routeService.deleteRoute(id);
        return ResponseEntity.ok().build();
    }
}
