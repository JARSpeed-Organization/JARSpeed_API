package com.jarspeed.api.route;

import com.jarspeed.api.util.ObjectUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
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
     * The User id.
     */
    private String userId;
    /**
     * The start date.
     */
    private LocalDateTime startDate;
    /**
     * The end date.
     */
    private LocalDateTime endDate;
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
    /**
     * The Elevation gain.
     */
    private double elevationGain;
    /**
     * The Elevation loss.
     */
    private double elevationLoss;

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
     * Gets user id.
     *
     * @return the user id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param pUserId the p user id
     */
    public void setUserId(final String pUserId) {
        userId = pUserId;
    }

    /**
     * Gets start date.
     *
     * @return the start date
     */
    public LocalDateTime getStartDate() {
        return startDate;
    }

    /**
     * Sets start date.
     *
     * @param pStartDate the p start date
     */
    public void setStartDate(final LocalDateTime pStartDate) {
        startDate = pStartDate;
    }

    /**
     * Gets end date.
     *
     * @return the end date
     */
    public LocalDateTime getEndDate() {
        return endDate;
    }

    /**
     * Sets end date.
     *
     * @param pEndDate the p end date
     */
    public void setEndDate(final LocalDateTime pEndDate) {
        endDate = pEndDate;
    }

    /**
     * Gets path.
     *
     * @return the path
     */
    public List<Coordinate> getPath() {
        return ObjectUtils.cloneListCoordinate(path);
    }

    /**
     * Sets path.
     *
     * @param pPath the path
     */
    public void setPath(final List<Coordinate> pPath) {
        this.path = ObjectUtils.cloneListCoordinate(pPath);
    }

    /**
     * Gets points of interest.
     *
     * @return the points of interest
     */
    public List<PointOfInterest> getPointsOfInterest() {
        return ObjectUtils.cloneListPointOfInterest(pointsOfInterest);
    }

    /**
     * Sets points of interest.
     *
     * @param pPointsOfInterest the points of interest
     */
    public void setPointsOfInterest(final List<PointOfInterest>
                                            pPointsOfInterest) {
        this.pointsOfInterest =
                ObjectUtils.cloneListPointOfInterest(pPointsOfInterest);
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return ObjectUtils.cloneString(title);
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
        return ObjectUtils.cloneString(description);
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
         * The Altitude.
         */
        private double altitude;

        /**
         * Default constructor.
         */
        public Coordinate() {
            // Empty constructor
        }

        /**
         * Constructor with all attributes.
         *
         * @param pLatitude  Latitude
         * @param pLongitude Longitude
         * @param pAltitude  the p altitude
         */
        public Coordinate(final double pLatitude, final double pLongitude,
                          final double pAltitude) {
            latitude = pLatitude;
            longitude = pLongitude;
            altitude = pAltitude;
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

        /**
         * Gets altitude.
         *
         * @return the altitude
         */
        public double getAltitude() {
            return altitude;
        }

        /**
         * Sets altitude.
         *
         * @param pAltitude the p altitude
         */
        public void setAltitude(double pAltitude) {
            altitude = pAltitude;
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
                    coordinates.getLongitude(), coordinates.getAltitude());
        }

        /**
         * Sets coordinates.
         *
         * @param pCoordinates the coordinates
         */
        public void setCoordinates(final Coordinate pCoordinates) {
            this.coordinates = new Coordinate(pCoordinates.getLatitude(),
                    pCoordinates.getLongitude(), pCoordinates.getAltitude());
        }
    }

    /**
     * Gets elevation gain.
     *
     * @return the elevation gain
     */
    public double getElevationGain() {
        return elevationGain;
    }

    /**
     * Sets elevation gain.
     *
     * @param pElevationGain the p elevation gain
     */
    public void setElevationGain(double pElevationGain) {
        elevationGain = pElevationGain;
    }

    /**
     * Gets elevation loss.
     *
     * @return the elevation loss
     */
    public double getElevationLoss() {
        return elevationLoss;
    }

    /**
     * Sets elevation loss.
     *
     * @param pElevationLoss the p elevation loss
     */
    public void setElevationLoss(double pElevationLoss) {
        elevationLoss = pElevationLoss;
    }
}
