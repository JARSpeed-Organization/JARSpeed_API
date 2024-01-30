package com.jarspeed.api.user;

import com.jarspeed.api.gender.Gender;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Entity representing a user in the system for tracking
 * tracking system.
 * <p>
 * This class is a JPA entity that defines the structure of the * user table
 * in the database.
 * user table in the database.
 * </p>
 */
@Entity
@Table(name = User.TABLE_NAME)
public class User {

    /**
     * The constant TABLE_NAME.
     */
    public static final String TABLE_NAME = "user";

    /**
     * The constant COLUMN_NAME_GENDER.
     */
    public static final String COLUMN_NAME_GENDER = "gender";

    /**
     * The Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * The Email.
     */
    private String email;

    /**
     * The Lastname.
     */
    private String lastname;

    /**
     * The Firstname.
     */
    private String firstname;

    /**
     * The Age.
     */
    private Integer age;

    /**
     * The Gender.
     */
    @ManyToOne
    @JoinColumn(name = COLUMN_NAME_GENDER, referencedColumnName
            = Gender.COLUMN_NAME_ID)
    private Gender gender;

    /**
     * The Weight.
     */
    private Double weight;

    /**
     * The Password.
     */
    private String password;

    /**
     * Instantiates a new User.
     */
    public User() {
        // Empty constructor
    }

    /**
     * Constructor with all attributs.
     *
     * @param pId        Id
     * @param pLastname  Lastname
     * @param pFirstname Firstname
     * @param pEmail     Email
     * @param pAge       Age
     * @param pGender    Gender
     * @param pWeight    Weight
     * @param pPassword  Password
     */
    public User(final Integer pId, final String pLastname,
                final String pFirstname, final String pEmail,
                final Integer pAge, final Gender pGender,
                final Double pWeight, final String pPassword) {
        this.id = pId;
        this.lastname = pLastname;
        this.firstname = pFirstname;
        this.email = pEmail;
        this.age = pAge;
        this.gender = pGender;
        this.weight = pWeight;
        this.password = pPassword;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets lastname.
     *
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Sets lastname.
     *
     * @param pLastname the p lastname
     */
    public void setLastname(String pLastname) {
        this.lastname = pLastname;
    }

    /**
     * Gets firstname.
     *
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Sets firstname.
     *
     * @param pFirstname the p firstname
     */
    public void setFirstname(String pFirstname) {
        this.firstname = pFirstname;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets age.
     *
     * @return the age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * Sets age.
     *
     * @param age the age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * Gets gender.
     *
     * @return the gender
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Sets gender.
     *
     * @param gender the gender
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * Gets weight.
     *
     * @return the weight
     */
    public Double getWeight() {
        return weight;
    }

    /**
     * Sets weight.
     *
     * @param weight the weight
     */
    public void setWeight(Double weight) {
        this.weight = weight;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
