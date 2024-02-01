package com.jarspeed.api.user;

import static org.junit.jupiter.api.Assertions.*;

import com.jarspeed.api.gender.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UserTest {

    private User user;
    private final Integer id = 1;
    private final String email = "test@example.com";
    private final String lastname = "Doe";
    private final String firstname = "John";
    private final String password = "password";
    private final Date birthdate = new Date();
    private final Gender gender = new Gender(1, "Male");
    private final Double weight = 75.0;

    @BeforeEach
    void setUp() {
        user = new User(id, lastname, firstname, email, birthdate, gender, weight, password);
    }

    @Test
    void testGetters() {
        assertEquals(id, user.getId());
        assertEquals(email, user.getEmail());
        assertEquals(lastname, user.getLastname());
        assertEquals(firstname, user.getFirstname());
        assertEquals(password, user.getPassword());
        assertEquals(birthdate, user.getBirthdate());
        assertEquals(gender, user.getGender());
        assertEquals(weight, user.getWeight());
    }

    @Test
    void testSetters() {
        // Set new values
        Integer newId = 2;
        String newEmail = "new@example.com";
        String newLastname = "Smith";
        String newFirstname = "Jane";
        String newPassword = "newPassword";
        Date newBirthdate = new Date();
        Gender newGender = new Gender(2, "Female");
        Double newWeight = 80.0;

        user.setId(newId);
        user.setEmail(newEmail);
        user.setLastname(newLastname);
        user.setFirstname(newFirstname);
        user.setPassword(newPassword);
        user.setBirthdate(newBirthdate);
        user.setGender(newGender);
        user.setWeight(newWeight);

        // Verify new values
        assertEquals(newId, user.getId());
        assertEquals(newEmail, user.getEmail());
        assertEquals(newLastname, user.getLastname());
        assertEquals(newFirstname, user.getFirstname());
        assertEquals(newPassword, user.getPassword());
        assertEquals(newBirthdate, user.getBirthdate());
        assertEquals(newGender, user.getGender());
        assertEquals(newWeight, user.getWeight());
    }

    @Test
    void testUpdateUserInfos() {
        // Prepare update request
        UserUpdateRequest updateRequest = new UserUpdateRequest("update@example.com", "Updated", "User", "updatedPassword", new Date(), new Gender(3, "Non-Binary"), 65.0);

        // Perform update
        user.updateUserInfos(updateRequest);

        // Verify updated values
        assertEquals(updateRequest.getEmail(), user.getEmail());
        assertEquals(updateRequest.getLastname(), user.getLastname());
        assertEquals(updateRequest.getFirstname(), user.getFirstname());
        assertEquals(updateRequest.getPassword(), user.getPassword());
        assertEquals(updateRequest.getBirthdate(), user.getBirthdate());
        assertEquals(updateRequest.getGender(), user.getGender());
        assertEquals(updateRequest.getWeight(), user.getWeight());
    }
    @Test
    void testTokenOperations() {
        // Initial value should be null if not set in the constructor
        assertNull(user.getToken(), "Initial token should be null");

        // Set token
        String token = "someTokenValue";
        user.setToken(token);

        // Verify token is correctly set
        assertEquals(token, user.getToken(), "Token should match the set value");
    }
}
