package com.jarspeed.api.user;


import com.jarspeed.api.sex.Sex;
import jakarta.persistence.*;

@Entity
@Table(name = "USER")
public class User {

    @Id
    private String email;

    private String lastname;
    private String firstname;
    private int age;
    private double weight;
    private String password;

    @ManyToOne
    @JoinColumn(name = "SEX", referencedColumnName = "ID")
    private Sex sex;

    // Getters et setters
    // ...
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Définissez les getters et setters pour les autres champs
}
