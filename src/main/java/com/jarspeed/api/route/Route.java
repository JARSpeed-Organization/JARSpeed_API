package com.jarspeed.api.route;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/**
 * Represents a route with a start point, end point, path, points of interest,
 * and additional information.
 * This class is annotated as a MongoDB document.
 */
@Document("Routes")
public class Route {
    /**
     * The Id.
     */
    @Id
    private String id;
    /**
     * The start date.
     */
    private Date startDate;
    /**
     * The end date.
     */
    private Date endDate;
    /**
     * The Path.
     */
    private List<Coordinate> path;
    /**
     * The Points of interest.
     */
    private List<PointOfInterest> pointsOfInterest;
    /**
     * The Title.
     */
    private String title;
    /**
     * The Description.
     */
    private String description;

    // Getters and Setters

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param pId the id
     */
    public void setId(final String pId) {
        this.id = pId;
    }

    /**
     * Gets start date.
     *
     * @return the start date
     */
    public Date getStartDate() {
        return (Date) startDate.clone();
    }

    /**
     * Sets start date.
     *
     * @param pStartDate the p start date
     */
    public void setStartDate(final Date pStartDate) {
        startDate = pStartDate != null ? new Date(pStartDate.getTime()) : null;
    }

    /**
     * Gets end date.
     *
     * @return the end date
     */
    public Date getEndDate() {
        return (Date) endDate.clone();
    }

    /**
     * Sets end date.
     *
     * @param pEndDate the p end date
     */
    public void setEndDate(final Date pEndDate) {
        endDate = pEndDate != null ? new Date(pEndDate.getTime()) : null;
    }

    /**
     * Gets path.
     *
     * @return the path
     */
    public List<Coordinate> getPath() {
        return List.copyOf(path);
    }

    /**
     * Sets path.
     *
     * @param pPath the path
     */
    public void setPath(final List<Coordinate> pPath) {
        this.path = List.copyOf(pPath);
    }

    /**
     * Gets points of interest.
     *
     * @return the points of interest
     */
    public List<PointOfInterest> getPointsOfInterest() {
        return List.copyOf(pointsOfInterest);
    }

    /**
     * Sets points of interest.
     *
     * @param pPointsOfInterest the points of interest
     */
    public void setPointsOfInterest(final List<PointOfInterest>
                                            pPointsOfInterest) {
        this.pointsOfInterest = List.copyOf(pPointsOfInterest);
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return String.copyValueOf(title.toCharArray());
    }

    /**
     * Sets title.
     *
     * @param pTitle the title
     */
    public void setTitle(final String pTitle) {
        this.title = pTitle;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return String.copyValueOf(description.toCharArray());
    }

    /**
     * Sets description.
     *
     * @param pDescription the description
     */
    public void setDescription(final String pDescription) {
        this.description = pDescription;
    }

    /**
     * Inner class representing geographical coordinates.
     */
    public static class Coordinate {
        /**
         * The Latitude.
         */
        private double latitude;
        /**
         * The Longitude.
         */
        private double longitude;

        /**
         * Constructor with all attributes.
         * @param pLatitude Latitude
         * @param pLongitude Longitude
         */
        public Coordinate(final double pLatitude, final double pLongitude) {
            latitude = pLatitude;
            longitude = pLongitude;
        }

        /**
         * Gets latitude.
         *
         * @return the latitude
         */
        public double getLatitude() {
            return latitude;
        }

        /**
         * Sets latitude.
         *
         * @param pLatitude the latitude
         */
        public void setLatitude(final double pLatitude) {
            this.latitude = pLatitude;
        }

        /**
         * Gets longitude.
         *
         * @return the longitude
         */
        public double getLongitude() {
            return longitude;
        }

        /**
         * Sets longitude.
         *
         * @param pLongitude the longitude
         */
        public void setLongitude(final double pLongitude) {
            this.longitude = pLongitude;
        }
    }

    /**
     * Inner class representing a point of interest along a route.
     */
    public static class PointOfInterest {
        /**
         * The Name.
         */
        private String name;
        /**
         * The Coordinates.
         */
        private Coordinate coordinates;

        /**
         * Gets name.
         *
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * Sets name.
         *
         * @param pName the name
         */
        public void setName(final String pName) {
            this.name = pName;
        }

        /**
         * Gets coordinates.
         *
         * @return the coordinates
         */
        public Coordinate getCoordinates() {
            return new Coordinate(coordinates.getLatitude(),
                    coordinates.getLongitude());
        }

        /**
         * Sets coordinates.
         *
         * @param pCoordinates the coordinates
         */
        public void setCoordinates(final Coordinate pCoordinates) {
            this.coordinates = new Coordinate(pCoordinates.getLatitude(),
                    pCoordinates.getLongitude());
        }
    }
}
