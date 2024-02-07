package com.jarspeed.api.gender;


import com.jarspeed.api.util.ObjectUtils;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Objects;

/**
 * The type Gender.
 */
@Entity
@Table(name = Gender.TABLE_NAME)
public class Gender implements Serializable {

    /**
     * Name of table gender in database (MySQL).
     */
    public static final String TABLE_NAME = "gender";

    /**
     * Name of column id in table gender.
     */
    public static final String COLUMN_NAME_ID = "id";

    /**
     * Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = COLUMN_NAME_ID)
    private Integer id;

    /**
     * Label.
     */
    private String label;

    /**
     * Default constructor.
     */
    public Gender() {
        // Empty constructor
    }

    /**
     * Constructor with all attributs.
     *
     * @param pId    Id of gender
     * @param pLabel Label of gender
     */
    public Gender(final Integer pId, final String pLabel) {
        this.id = pId;
        this.label = pLabel;
    }

    /**
     * Gets the id.
     *
     * @return value of Id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param pId New id
     */
    public void setId(final Integer pId) {
        id = pId;
    }

    /**
     * Gets the label.
     *
     * @return value of label
     */
    public String getLabel() {
        return ObjectUtils.cloneString(label);
    }

    /**
     * Sets the label.
     *
     * @param pLabel new label
     */
    public void setLabel(final String pLabel) {
        label = pLabel;
    }

    /**
     * Check if this equal to p0.
     * @param pO Object to check
     * @return True if all attributes are equal to p0
     */
    @Override
    public boolean equals(final Object pO) {
        if (this == pO) {
            return true;
        }
        if (pO == null || getClass() != pO.getClass()) {
            return false;
        }
        Gender gender = (Gender) pO;
        return Objects.equals(id, gender.id)
                && Objects.equals(label, gender.label);
    }

    /**
     * Hash code.
     * @return Hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, label);
    }
}
