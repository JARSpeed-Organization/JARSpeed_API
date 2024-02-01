package com.jarspeed.api.gender;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * The type Gender.
 */
@Entity
@Table(name = Gender.TABLE_NAME)
public class Gender {

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
        return label;
    }

    /**
     * Sets the label.
     *
     * @param pLabel new label
     */
    public void setLabel(final String pLabel) {
        label = pLabel;
    }


}
