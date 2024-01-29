package com.jarspeed.api.user;

import java.io.Serializable;

/**
 * Classe pour gérer les données d'inscription d'un utilisateur.
 */
public class UserRegistrationRequest implements Serializable {

    private String lastname;
    private String firstname;
    private String email;
    private String password;

    public UserRegistrationRequest() {
    }

    public UserRegistrationRequest(String lastname, String firstname, String email, String password) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.password = password;
    }

    // Getters
    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    // Setters
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserRegistrationRequest{" +
                "lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + "[PROTECTED]" + '\'' +
                '}';
    }
}
