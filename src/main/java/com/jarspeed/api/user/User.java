package com.jarspeed.api.user;

import com.jarspeed.api.gender.Gender;
import jakarta.persistence.*;

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

    public static final String TABLE_NAME = "user";

    public static final String COLUMN_NAME_GENDER = "gender";

    @Id
    private Integer id;

    private String email;

    private String lastname;

    private String firstname;

    private Date birthdate;

    @ManyToOne
    @JoinColumn(name = COLUMN_NAME_GENDER, referencedColumnName = Gender.COLUMN_NAME_ID)
    private Gender gender;

    private Double weight;

    private String password;

    public User() {
    }

    public User(String pLastname, String pFirstname, String pEmail, Date pBirthdate, Gender pGender, Double pWeight, String pPassword) {
        this.lastname = pLastname;
        this.firstname = pFirstname;
        this.email = pEmail;
        this.birthdate = pBirthdate;
        this.gender = pGender;
        this.weight = pWeight;
        this.password = pPassword;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String pLastname) {
        this.lastname = pLastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String pFirstname) {
        this.firstname = pFirstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
