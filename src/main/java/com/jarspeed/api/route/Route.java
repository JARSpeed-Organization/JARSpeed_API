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
    private CustomLineString path;
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

    /**
     * The Time.
     */
    private String time;

    /**
     * The Speed.
     */
    private String speed;

    /**
     * The Distance.
     */
    private String distance;

    /**
     * Sets time.
     *
     * @param pTime the p time
     */
    public void setTime(final String pTime) {
        time = pTime;
    }

    /**
     * Sets speed.
     *
     * @param pSpeed the p speed
     */
    public void setSpeed(final String pSpeed) {
        speed = pSpeed;
    }

    /**
     * Sets distance.
     *
     * @param pDistance the p distance
     */
    public void setDistance(final String pDistance) {
        distance = pDistance;
    }

    /**
     * Gets time.
     *
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * Gets speed.
     *
     * @return the speed
     */
    public String getSpeed() {
        return speed;
    }

    /**
     * Gets distance.
     *
     * @return the distance
     */
    public String getDistance() {
        return distance;
    }
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
    public CustomLineString getPath() {
        try {
            return path != null ? path.clone() : null;
        } catch (CloneNotSupportedException pE) {
            return null;
        }
    }

    /**
     * Sets path.
     *
     * @param pPath the path
     */
    public void setPath(final CustomLineString pPath) {
        try {
            path = pPath != null ? pPath.clone() : null;
        } catch (CloneNotSupportedException e) {
            path = null;
        }
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
        private CustomPoint point;

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
        public CustomPoint getPoint() {
            try {
                return point != null ? point.clone() : null;
            } catch (CloneNotSupportedException pE) {
                return new CustomPoint(List.of(-1.0, -1.0));
            }
        }

        /**
         * Sets coordinates.
         *
         * @param pPoint the coordinates
         */
        public void setPoint(final CustomPoint pPoint) {
            try {
                point = pPoint != null ? pPoint.clone() : null;
            } catch (CloneNotSupportedException pE) {
                point = null;
            }
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
    public void setElevationGain(final double pElevationGain) {
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
    public void setElevationLoss(final double pElevationLoss) {
        elevationLoss = pElevationLoss;
    }
}
