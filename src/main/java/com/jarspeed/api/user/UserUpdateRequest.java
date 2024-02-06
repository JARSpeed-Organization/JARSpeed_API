package com.jarspeed.api.user;

import com.jarspeed.api.gender.Gender;

import java.io.Serializable;
import java.util.Date;

/**
 * The type User update request.
 */
public class UserUpdateRequest implements Serializable {
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
     * The Password.
     */
    private String password;
    /**
     * The Birthdate.
     */
    private Date birthdate;
    /**
     * The Gender.
     */
    private Gender gender;
    /**
     * The Weight.
     */
    private Double weight;


    /**
     * Instantiates a new User update request.
     */
// Constructeurs
    public UserUpdateRequest() {
    }


    /**
     * Instantiates a new User update request.
     *
     * @param pEmail     the p email
     * @param pLastname  the p lastname
     * @param pFirstname the p firstname
     * @param pPassword  the p password
     * @param pBirthdate the p birthdate
     * @param pGender    the p gender
     * @param pWeight    the p weight
     */
    public UserUpdateRequest(final String pEmail, final String pLastname,
                             final String pFirstname,
                             final String pPassword, final Date pBirthdate,
                             final Gender pGender, final Double pWeight) {
        this.email = pEmail;
        this.lastname = pLastname;
        this.firstname = pFirstname;
        this.password = pPassword;
        this.birthdate = pBirthdate != null ? (Date) pBirthdate.clone() : null;
        this.gender = pGender != null ? new Gender(pGender.getId(),
                pGender.getLabel()) : null;
        this.weight = pWeight;
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
     * @param pEmail the email
     */
    public void setEmail(final String pEmail) {
        this.email = String.copyValueOf(pEmail.toCharArray());
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
     * @param pLastname the lastname
     */
    public void setLastname(final String pLastname) {
        this.lastname = String.copyValueOf(pLastname.toCharArray());
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
     * @param pFirstname the firstname
     */
    public void setFirstname(final String pFirstname) {
        this.firstname = String.copyValueOf(pFirstname.toCharArray());
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
     * @param pPassword the password
     */
    public void setPassword(final String pPassword) {
        this.password = String.copyValueOf(pPassword.toCharArray());
    }

    /**
     * Gets birthdate.
     *
     * @return the birthdate
     */
    public Date getBirthdate() {
        return birthdate != null ? (Date) birthdate.clone() : null;
    }

    /**
     * Sets birthdate.
     *
     * @param pBirthdate the birthdate
     */
    public void setBirthdate(final Date pBirthdate) {
        this.birthdate = pBirthdate != null ? (Date) pBirthdate.clone() : null;
    }

    /**
     * Gets gender.
     *
     * @return the gender
     */
    public Gender getGender() {
        return gender != null
                ? new Gender(gender.getId(), gender.getLabel()) : null;
    }

    /**
     * Sets gender.
     *
     * @param pGender the gender
     */
    public void setGender(final Gender pGender) {
        this.gender = pGender != null ? new Gender(pGender.getId(),
                pGender.getLabel()) : null;
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
     * @param pWeight the weight
     */
    public void setWeight(final Double pWeight) {
        this.weight = pWeight;
    }
}
