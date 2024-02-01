package com.jarspeed.api.user;

import java.io.Serializable;

/**
 * Classe pour gérer les données d'inscription d'un utilisateur.
 */
public class UserRegistrationRequest implements Serializable {

    /**
     * The Lastname.
     */
    private String lastname;
    /**
     * The Firstname.
     */
    private String firstname;
    /**
     * The Email.
     */
    private String email;
    /**
     * The Password.
     */
    private String password;

    /**
     * Instantiates a new User registration request.
     */
    public UserRegistrationRequest() {
    }

    /**
     * Instantiates a new User registration request.
     *
     * @param pLastname  the p lastname
     * @param pFirstname the p firstname
     * @param pEmail     the p email
     * @param pPassword  the p password
     */
    public UserRegistrationRequest(final String pLastname,
                                   final String pFirstname,
                                   final String pEmail,
                                   final String pPassword) {
        this.lastname = pLastname;
        this.firstname = pFirstname;
        this.email = pEmail;
        this.password = pPassword;
    }

    /**
     * Gets lastname.
     *
     * @return the lastname
     */
// Getters
    public String getLastname() {
        return lastname;
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
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
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
     * Sets lastname.
     *
     * @param pLastname the p lastname
     */
// Setters
    public void setLastname(final String pLastname) {
        this.lastname = pLastname;
    }

    /**
     * Sets firstname.
     *
     * @param pFirstname the p firstname
     */
    public void setFirstname(final String pFirstname) {
        this.firstname = pFirstname;
    }

    /**
     * Sets email.
     *
     * @param pEmail the p email
     */
    public void setEmail(final String pEmail) {
        this.email = pEmail;
    }

    /**
     * Sets password.
     *
     * @param pPassword the p password
     */
    public void setPassword(final String pPassword) {
        this.password = pPassword;
    }

    /**
     * Get toString.
     *
     * @return toString version of some datas.
     */
    @Override
    public String toString() {
        return "UserRegistrationRequest{"
                + "lastname='" + lastname + '\''
                + ", firstname='" + firstname + '\''
                + ", email='" + email + '\''
                + ", password='" + "[PROTECTED]" + '\''
                + '}';
    }
}
