package com.jarspeed.api.user;

import com.jarspeed.api.gender.Gender;
import jakarta.persistence.Entity;
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
     * Name of table user in database (MySQL).
     */
    public static final String TABLE_NAME = "user";

    /**
     * Name of column gender id in table user.
     */
    public static final String COLUMN_NAME_GENDER = "gender";

    /**
     * Id.
     */
    @Id
    private Integer id;

    /**
     * Email.
     */
    private String email;

    /**
     * Lastname.
     */
    private String lastname;

    /**
     * Firstname.
     */
    private String firstname;

    /**
     * Age.
     */
    private Integer age;

    /**
     * Gender.
     */
    @ManyToOne
    @JoinColumn(name = COLUMN_NAME_GENDER,
            referencedColumnName = Gender.COLUMN_NAME_ID)
    private Gender gender;

    /**
     * Weight.
     */
    private Double weight;

    /**
     * Password.
     */
    private String password;

    /**
     * Default constructor.
     */
    public User() {
        // Empty constructor
    }

    /**
     * Constructor with all attributs.
     * @param pLastname Lastname
     * @param pFirstname Firstname
     * @param pEmail Email
     * @param pAge Age
     * @param pGender Gender
     * @param pWeight Weight
     * @param pPassword Password
     */
    public User(final String pLastname, final String pFirstname,
                final String pEmail, final Integer pAge, final Gender pGender,
                final Double pWeight, final String pPassword) {
        this.lastname = pLastname;
        this.firstname = pFirstname;
        this.email = pEmail;
        this.age = pAge;
        this.gender = pGender;
        this.weight = pWeight;
        this.password = pPassword;
    }

    /**
     * Gets the id.
     * @return value of id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the id.
     * @param pId new id
     */
    public void setId(final Integer pId) {
        id = pId;
    }

    /**
     * Gets the email.
     * @return value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email.
     * @param pEmail new email
     */
    public void setEmail(final String pEmail) {
        email = pEmail;
    }

    /**
     * Gets the lastname.
     * @return value of lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Sets the lastname.
     * @param pLastname new lastname
     */
    public void setLastname(final String pLastname) {
        lastname = pLastname;
    }

    /**
     * Gets the firstname.
     * @return value of firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Sets the firstname.
     * @param pFirstname new firstname
     */
    public void setFirstname(final String pFirstname) {
        firstname = pFirstname;
    }

    /**
     * Gets the age.
     * @return value of age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * Sets the age.
     * @param pAge new age
     */
    public void setAge(final Integer pAge) {
        age = pAge;
    }

    /**
     * Gets the gender.
     * @return value of gender
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Sets the gender.
     * @param pGender new gender
     */
    public void setGender(final Gender pGender) {
        gender = pGender;
    }

    /**
     * Gets the weight.
     * @return value of weight
     */
    public Double getWeight() {
        return weight;
    }

    /**
     * Sets the weight.
     * @param pWeight new weight
     */
    public void setWeight(final Double pWeight) {
        weight = pWeight;
    }

    /**
     * Gets the password.
     * @return value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     * @param pPassword new password
     */
    public void setPassword(final String pPassword) {
        password = pPassword;
    }
}
