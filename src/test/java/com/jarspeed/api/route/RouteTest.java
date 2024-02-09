package com.jarspeed.api.route;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

public class RouteTest {
    private Route route;

    @BeforeEach
    public void setUp() {
        route = new Route();
    }

    @Test
    public void testGetAndSetId() {
        String id = "route123";
        route.setId(id);
        assertEquals(id, route.getId());
    }

    @Test
    public void testGetAndSetStartDate() {
        LocalDateTime startDate = LocalDateTime.MAX;
        route.setStartDate(startDate);
        assertEquals(startDate, route.getStartDate());
    }

    @Test
    public void testGetAndSetEndDate() {
        LocalDateTime endDate = LocalDateTime.MIN;
        route.setEndDate(endDate);
        assertEquals(endDate, route.getEndDate());
    }

    @Test
    public void testGetAndSetPath() {
        Route.Coordinate coordinate = new Route.Coordinate();
        coordinate.setLatitude(10.0);
        coordinate.setLongitude(20.0);
        route.setPath(Arrays.asList(coordinate));
        assertEquals(1, route.getPath().size());
        assertEquals(10.0, route.getPath().get(0).getLatitude());
        assertEquals(20.0, route.getPath().get(0).getLongitude());
    }

    @Test
    public void testGetAndSetPointsOfInterest() {
        Route.PointOfInterest poi = new Route.PointOfInterest();
        poi.setName("Point of Interest");
        Route.Coordinate coordinate = new Route.Coordinate();
        coordinate.setLatitude(30.0);
        coordinate.setLongitude(40.0);
        poi.setCoordinates(coordinate);
        route.setPointsOfInterest(Arrays.asList(poi));
        assertEquals(1, route.getPointsOfInterest().size());
        assertEquals("Point of Interest", route.getPointsOfInterest().get(0).getName());
        assertEquals(30.0, route.getPointsOfInterest().get(0).getCoordinates().getLatitude());
        assertEquals(40.0, route.getPointsOfInterest().get(0).getCoordinates().getLongitude());
    }

    @Test
    public void testGetAndSetTitle() {
        String title = "Test Route";
        route.setTitle(title);
        assertEquals(title, route.getTitle());
    }

    @Test
    public void testGetAndSetDescription() {
        String description = "This is a test route";
        route.setDescription(description);
        assertEquals(description, route.getDescription());
    }
}
