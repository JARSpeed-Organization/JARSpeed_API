package com.jarspeed.api.route;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;
import java.util.List;

/**
 * Represents a route with a start point, end point, path, points of interest, and additional information.
 * This class is annotated as a MongoDB document.
 */
@Document
public class Route {
    @Id
    private String id;
    private Date date;
    private Coordinate startPoint;
    private Coordinate endPoint;
    private List<Coordinate> path;
    private List<PointOfInterest> pointsOfInterest;
    private String title;
    private String description;

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Coordinate getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Coordinate startPoint) {
        this.startPoint = startPoint;
    }

    public Coordinate getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Coordinate endPoint) {
        this.endPoint = endPoint;
    }

    public List<Coordinate> getPath() {
        return path;
    }

    public void setPath(List<Coordinate> path) {
        this.path = path;
    }

    public List<PointOfInterest> getPointsOfInterest() {
        return pointsOfInterest;
    }

    public void setPointsOfInterest(List<PointOfInterest> pointsOfInterest) {
        this.pointsOfInterest = pointsOfInterest;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Inner class representing geographical coordinates.
     */
    public static class Coordinate {
        private double latitude;
        private double longitude;

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }
    }

    /**
     * Inner class representing a point of interest along a route.
     */
    public static class PointOfInterest {
        private String name;
        private Coordinate coordinates;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Coordinate getCoordinates() {
            return coordinates;
        }

        public void setCoordinates(Coordinate coordinates) {
            this.coordinates = coordinates;
        }
    }
}
