package com.jarspeed.api.route;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

        CustomLineString customLineString = new CustomLineString();
        customLineString.setCoordinates(List.of(List.of(15.0, 48.0)));
        existingRoute = new Route();
        existingRoute.setId("1");
        existingRoute.setTitle("Title");
        existingRoute.setDescription("Description");
        existingRoute.setStartDate(LocalDateTime.MAX);
        existingRoute.setEndDate(LocalDateTime.MAX);
        existingRoute.setPath(customLineString);
        existingRoute.setPointsOfInterest(List.of());
    }

    @Test
    public void testGetAllRoutesByUserId() {
        // Given
        String userId = "user123";
        List<Route> expectedRoutes = new ArrayList<>();
        expectedRoutes.add(new Route());
        expectedRoutes.add(new Route());

        // Mock repository behavior
        when(routeRepository.findAllByUserId(userId)).thenReturn(expectedRoutes);

        // When
        List<Route> actualRoutes = routeService.getAllRoutesByUserId(userId);

        // Then
        assertEquals(expectedRoutes.size(), actualRoutes.size());
        // Add more assertions as needed
    }

    @Test
    public void testUpdateRoute_Success() {
        // Arrange
        CustomLineString updatedCustomLineString = new CustomLineString();
        updatedCustomLineString.setCoordinates(List.of(List.of(11.0, 21.0)));
        Route updatedRouteDetails = new Route();
        updatedRouteDetails = new Route();
        updatedRouteDetails.setId("1");
        updatedRouteDetails.setTitle("Title");
        updatedRouteDetails.setDescription("Description");
        updatedRouteDetails.setStartDate(LocalDateTime.MIN);
        updatedRouteDetails.setEndDate(LocalDateTime.MIN);
        updatedRouteDetails.setPath(updatedCustomLineString);
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
        assertEquals(updatedRouteDetails.getPath().getCoordinates().get(0),
                updatedRoute.getPath().getCoordinates().get(0));
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
