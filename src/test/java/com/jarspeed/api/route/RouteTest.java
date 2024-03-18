package com.jarspeed.api.route;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
        CustomLineString customLineString = new CustomLineString();
        customLineString.setCoordinates(List.of(List.of(10.0, 20.0)));
        route.setPath(customLineString);
        assertEquals(1, route.getPath().getCoordinates().size());
        assertEquals(10.0, route.getPath().getCoordinates().get(0).get(0));
        assertEquals(20.0, route.getPath().getCoordinates().get(0).get(1));
    }

    @Test
    public void testGetAndSetPointsOfInterest() {
        Route.PointOfInterest poi = new Route.PointOfInterest();
        poi.setName("Point of Interest");
        CustomPoint customPoint = new CustomPoint();
        customPoint.setCoordinates(List.of(30.0, 40.0));
        poi.setPoint(customPoint);
        route.setPointsOfInterest(Arrays.asList(poi));

        assertEquals(1, route.getPointsOfInterest().size());
        assertEquals("Point of Interest", route.getPointsOfInterest().get(0).getName());

        assertNotNull(route.getPointsOfInterest());
        assertNotNull(route.getPointsOfInterest().get(0).getPoint());
        assertNotNull(route.getPointsOfInterest().get(0).getPoint().getCoordinates());
        assertEquals(30.0,
                route.getPointsOfInterest().get(0).getPoint().getCoordinates().get(0));
        assertEquals(40.0,
                route.getPointsOfInterest().get(0).getPoint().getCoordinates().get(1));
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
