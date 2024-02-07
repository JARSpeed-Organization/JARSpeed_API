package com.jarspeed.api.util;

import com.jarspeed.api.gender.Gender;
import com.jarspeed.api.route.Route;

import java.util.Date;
import java.util.List;

/**
 * Utils methods of object.
 */
public final class ObjectUtils {

    /**
     * Default constructor.
     */
    private ObjectUtils() {
        // Utils class
    }

    /**
     * Clone a date.
     * @param pDate Date to clone
     * @return a clone of date if this date is not null
     */
    public static Date cloneDate(final Date pDate) {
        return pDate != null ? (Date) pDate.clone() : null;
    }

    /**
     * Clone a list of coordinates.
     * @param pList List to clone
     * @return a clone of list if this list is not null
     */
    public static List<Route.Coordinate> cloneListCoordinate(
            final List<Route.Coordinate> pList) {
        return pList != null ? List.copyOf(pList) : null;
    }

    /**
     * Clone a list of points of interest.
     * @param pList List to clone
     * @return a clone of list if this list is not null
     */
    public static List<Route.PointOfInterest> cloneListPointOfInterest(
            final List<Route.PointOfInterest> pList) {
        return pList != null ? List.copyOf(pList) : null;
    }

    /**
     * Clone a string.
     * @param pString String to clone
     * @return a clone of string if this string is not null
     */
    public static String cloneString(final String pString) {
        return pString != null ? String.copyValueOf(pString.toCharArray())
                : null;
    }

    /**
     * Clone a gender.
     * @param pGender Gender to clone
     * @return a clone of gender if this gender is not null
     */
    public static Gender cloneGender(final Gender pGender) {
        return pGender != null ? new Gender(pGender.getId(),
                pGender.getLabel()) : null;
    }
}
