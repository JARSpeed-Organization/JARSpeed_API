package com.jarspeed.api.gender;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GenderControllerTest {

    private final List<Gender> GENDERS = List.of(new Gender(1, "Femme"), new Gender(2, "Homme"));

    @Mock
    private GenderRepository genderRepository;

    @InjectMocks
    private GenderController genderController;

    @Test
    void getAll() {
        when(genderRepository.findAll()).thenReturn(GENDERS);
        assertEquals(GENDERS, genderController.getAll());
    }
}
