package com.jarspeed.api.user;

import com.jarspeed.api.gender.Gender;
import com.jarspeed.api.util.ObjectUtils;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Date;

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
     * The Birthdate.
     */
    private Date birthdate;

    /**
     * The Token.
     */
    private String token;

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
     * @param pBirthdate Birthdate
     * @param pGender    Gender
     * @param pWeight    Weight
     * @param pPassword  Password
     */
    public User(final Integer pId, final String pLastname,
                final String pFirstname, final String pEmail,
                final Date pBirthdate, final Gender pGender,
                final Double pWeight, final String pPassword) {
        this.id = pId;
        this.lastname = pLastname;
        this.firstname = pFirstname;
        this.email = pEmail;
        this.birthdate = ObjectUtils.cloneDate(pBirthdate);
        this.gender = ObjectUtils.cloneGender(pGender);
        this.weight = pWeight;
        this.password = pPassword;
    }

    /**
     * Update user information with the new data.
     *
     * @param pUpdateRequest The object with the new information.
     */
    public void updateUserInfos(final UserUpdateRequest pUpdateRequest) {
        if (pUpdateRequest.getEmail() != null) {
            this.email = pUpdateRequest.getEmail();
        }
        if (pUpdateRequest.getFirstname() != null) {
            this.firstname = pUpdateRequest.getFirstname();
        }
        if (pUpdateRequest.getLastname() != null) {
            this.lastname = pUpdateRequest.getLastname();
        }
        if (pUpdateRequest.getPassword() != null) {
            this.password = pUpdateRequest.getPassword();
        }
        if (pUpdateRequest.getBirthdate() != null) {
            this.birthdate = pUpdateRequest.getBirthdate();
        }
        if (pUpdateRequest.getGender() != null) {
            this.gender = pUpdateRequest.getGender();
        }
        if (pUpdateRequest.getWeight() != null) {
            this.weight = pUpdateRequest.getWeight();
        }
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
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return ObjectUtils.cloneString(email);
    }

    /**
     * Gets lastname.
     *
     * @return the lastname
     */
    public String getLastname() {
        return ObjectUtils.cloneString(lastname);
    }

    /**
     * Gets firstname.
     *
     * @return the firstname
     */
    public String getFirstname() {
        return ObjectUtils.cloneString(firstname);
    }

    /**
     * Sets id.
     *
     * @param pId the p id
     */
    public void setId(final Integer pId) {
        id = pId;
    }

    /**
     * Sets email.
     *
     * @param pEmail the p email
     */
    public void setEmail(final String pEmail) {
        email = pEmail;
    }

    /**
     * Sets lastname.
     *
     * @param pLastname the p lastname
     */
    public void setLastname(final String pLastname) {
        lastname = pLastname;
    }

    /**
     * Sets firstname.
     *
     * @param pFirstname the p firstname
     */
    public void setFirstname(final String pFirstname) {
        firstname = pFirstname;
    }

    /**
     * Sets birthdate.
     *
     * @param pBirthdate the p birthdate
     */
    public void setBirthdate(final Date pBirthdate) {
        birthdate = ObjectUtils.cloneDate(pBirthdate);
    }

    /**
     * Sets gender.
     *
     * @param pGender the p gender
     */
    public void setGender(final Gender pGender) {
        gender = ObjectUtils.cloneGender(pGender);
    }

    /**
     * Sets weight.
     *
     * @param pWeight the p weight
     */
    public void setWeight(final Double pWeight) {
        weight = pWeight;
    }

    /**
     * Sets password.
     *
     * @param pPassword the p password
     */
    public void setPassword(final String pPassword) {
        password = pPassword;
    }

    /**
     * Gets birthdate.
     *
     * @return the birthdate
     */
    public Date getBirthdate() {
        return ObjectUtils.cloneDate(birthdate);
    }

    /**
     * Gets gender.
     *
     * @return the gender
     */
    public Gender getGender() {
        return ObjectUtils.cloneGender(gender);
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
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets token.
     *
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets token.
     *
     * @param pToken the token
     */
    public void setToken(final String pToken) {
        this.token = pToken;
    }
}
