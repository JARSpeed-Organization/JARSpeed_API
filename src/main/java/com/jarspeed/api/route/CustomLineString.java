package com.jarspeed.api.route;

import com.jarspeed.api.util.ObjectUtils;

import java.util.List;

/**
 * The type Custom line string.
 */
public final class CustomLineString implements Cloneable {
    /**
     * The Type.
     */
    private String type = "LineString";
    /**
     * The Coordinates.
     */
    private List<List<Double>> coordinates;

    /**
     * Instantiates a new Custom line string.
     */
    public CustomLineString() {
        // Empty constructor
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
        type = pType;
    }

    /**
     * Gets coordinates.
     *
     * @return the coordinates
     */
    public List<List<Double>> getCoordinates() {
        return ObjectUtils.clonePath(coordinates);
    }

    /**
     * Sets coordinates.
     *
     * @param pCoordinates the p coordinates
     */
    public void setCoordinates(final List<List<Double>> pCoordinates) {
        coordinates = ObjectUtils.clonePath(pCoordinates);
    }

    /**
     * Override the clone method to provide a deep copy of CustomLineString
     * object.
     *
     * @return the cloned CustomLineString object
     * @throws CloneNotSupportedException if cloning is not supported
     */
    public CustomLineString clone() throws CloneNotSupportedException {
        CustomLineString clone = (CustomLineString) super.clone();
        clone.setCoordinates(ObjectUtils.clonePath(coordinates));
        return clone;
    }
}
