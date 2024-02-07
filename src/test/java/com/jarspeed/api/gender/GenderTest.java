package com.jarspeed.api.gender;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class GenderTest {

    @Test
    void testEqualsSuccess() {
        Gender gender = new Gender(1, "Homme");
        Gender newGender = new Gender(1, "Homme");
        // Meme reference
        assertTrue(gender.equals(gender));
        // Tous les champs sont egaux
        assertTrue(gender.equals(newGender));
    }

    @Test
    void testEqualsFail() {
        Gender gender = new Gender(1, "Homme");
        Gender newGender;

        // Pas le meme type
        assertFalse(gender.equals(""));
        // Equals to null
        assertFalse(gender.equals(null));
        // Meme id mais label different
        newGender = new Gender(1, "Femme");
        assertFalse(gender.equals(newGender));
        // Tous les champs differents
        newGender = new Gender(2, "Femme");
        assertFalse(gender.equals(newGender));
    }

    @Test
    void testHashCode() {
        Integer id = Integer.getInteger("1");
        String label = "Homme";
        Gender gender = new Gender(id, label);
        assertEquals(Objects.hash(id, label), gender.hashCode());
    }
}