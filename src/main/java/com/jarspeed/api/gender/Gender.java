package com.jarspeed.api.gender;

import jakarta.persistence.*;

@Entity
@Table(name = Gender.TABLE_NAME)
public class Gender {

    public static final String TABLE_NAME = "gender";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String label;

    public Gender() {
    }

    public Gender(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
