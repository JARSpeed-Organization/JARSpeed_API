package com.jarspeed.api.user;

import com.jarspeed.api.gender.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserUpdateRequestTest {

    private UserUpdateRequest request;
    private final String email = "test@example.com";
    private final String lastname = "Doe";
    private final String firstname = "John";
    private final String password = "password";
    private final Date birthdate = new Date();
    private final Gender gender = new Gender(1, "Male");
    private final Double weight = 75.0;

    @BeforeEach
    void setUp() {
        // Initialize your request object here
        request = new UserUpdateRequest(email, lastname, firstname, password, birthdate, gender, weight);
    }

    @Test
    void testGettersAndSetters() {
        // Test getters
        assertEquals(email, request.getEmail());
        assertEquals(lastname, request.getLastname());
        assertEquals(firstname, request.getFirstname());
        assertEquals(password, request.getPassword());
        assertEquals(birthdate, request.getBirthdate());
        assertEquals(gender, request.getGender());
        assertEquals(weight, request.getWeight());

        // Test setters with new values
        String newEmail = "new@example.com";
        String newLastname = "Smith";
        String newFirstname = "Jane";
        String newPassword = "newPassword";
        Date newBirthdate = new Date();
        Gender newGender = new Gender(2, "Female");
        Double newWeight = 80.0;

        request.setEmail(newEmail);
        request.setLastname(newLastname);
        request.setFirstname(newFirstname);
        request.setPassword(newPassword);
        request.setBirthdate(newBirthdate);
        request.setGender(newGender);
        request.setWeight(newWeight);

        // Assert the changes
        assertEquals(newEmail, request.getEmail());
        assertEquals(newLastname, request.getLastname());
        assertEquals(newFirstname, request.getFirstname());
        assertEquals(newPassword, request.getPassword());
        assertEquals(newBirthdate, request.getBirthdate());
        assertEquals(newGender, request.getGender());
        assertEquals(newWeight, request.getWeight());
    }

}
