package com.jarspeed.api.route;

import com.jarspeed.api.util.ObjectUtils;

import java.util.List;

/**
 * The type Custom point.
 */
public class CustomPoint implements Cloneable {
    /**
     * The Type.
     */
    private String type = "Point";
    /**
     * The Coordinates.
     */
    private List<Double> coordinates;

    /**
     * Instantiates a new Custom point.
     */
    public CustomPoint() {
        // Empty constuctor
    }

    /**
     * Instantiates a new Custom point.
     *
     * @param pCoordinates the coordinates
     */
    public CustomPoint(final List<Double> pCoordinates) {
        coordinates = ObjectUtils.cloneListCoordinates(pCoordinates);
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Not used this method.
     *
     * @param pType the p type
     */
    public void setType(final String pType) {
        // Empty method (JPA)
    }

    /**
     * Gets coordinates.
     *
     * @return the coordinates
     */
    public List<Double> getCoordinates() {
        return ObjectUtils.cloneListCoordinates(coordinates);
    }

    /**
     * Sets coordinates.
     *
     * @param pCoordinates the p coordinates
     */
    public void setCoordinates(final List<Double> pCoordinates) {
        coordinates = ObjectUtils.cloneListCoordinates(pCoordinates);
    }

    /**
     * Override the clone method to provide a deep copy of CustomPoint object.
     *
     * @return the cloned CustomPoint object
     * @throws CloneNotSupportedException if cloning is not supported
     */
    @Override
    public CustomPoint clone() throws CloneNotSupportedException {
        CustomPoint cloned = (CustomPoint) super.clone();
        // Perform deep copy of coordinates list
        cloned.coordinates = ObjectUtils.cloneListCoordinates(coordinates);
        return cloned;
    }
}
