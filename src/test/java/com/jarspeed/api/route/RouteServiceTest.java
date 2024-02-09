package com.jarspeed.api.route;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RouteServiceTest {

    @Mock
    private RouteRepository routeRepository;

    @InjectMocks
    private RouteService routeService;

    private Route existingRoute;

    @BeforeEach
    public void setup() {
        Route.Coordinate coordinate = new Route.Coordinate();
        coordinate.setLongitude(15);
        coordinate.setLatitude(48);
        existingRoute = new Route();
        existingRoute.setId("1");
        existingRoute.setTitle("Title");
        existingRoute.setDescription("Description");
        existingRoute.setStartDate(LocalDateTime.MAX);
        existingRoute.setEndDate(LocalDateTime.MAX);
        existingRoute.setPath(List.of(coordinate));
        existingRoute.setPointsOfInterest(List.of());
    }

    @Test
    public void testUpdateRoute_Success() {
        // Arrange
        Route updatedRouteDetails = new Route();
        Route.Coordinate coordinate = new Route.Coordinate();
        coordinate.setLongitude(15);
        coordinate.setLatitude(48);
        updatedRouteDetails = new Route();
        updatedRouteDetails.setId("1");
        updatedRouteDetails.setTitle("Title");
        updatedRouteDetails.setDescription("Description");
        updatedRouteDetails.setStartDate(LocalDateTime.MIN);
        updatedRouteDetails.setEndDate(LocalDateTime.MIN);
        updatedRouteDetails.setPath(List.of(coordinate));
        updatedRouteDetails.setPointsOfInterest(List.of());

        when(routeRepository.findById("1")).thenReturn(Optional.of(existingRoute));
        when(routeRepository.save(existingRoute)).thenReturn(existingRoute);

        // Act
        Route updatedRoute = routeService.updateRoute("1", updatedRouteDetails);

        // Assert
        assertNotNull(updatedRoute);
        assertEquals(updatedRouteDetails.getTitle(), updatedRoute.getTitle());
        assertEquals(updatedRouteDetails.getDescription(), updatedRoute.getDescription());
        assertEquals(updatedRouteDetails.getStartDate(), updatedRoute.getStartDate());
        assertEquals(updatedRouteDetails.getEndDate(), updatedRoute.getEndDate());
        assertEquals(updatedRouteDetails.getPath(), updatedRoute.getPath());
        assertEquals(updatedRouteDetails.getPointsOfInterest(), updatedRoute.getPointsOfInterest());
        verify(routeRepository, times(1)).findById("1");
        verify(routeRepository, times(1)).save(existingRoute);
    }

    @Test
    public void testUpdateRoute_NonExistentRoute() {
        // Arrange
        when(routeRepository.findById("1")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> routeService.updateRoute("1", new Route()));
    }
}
