package com.jarspeed.api.gender;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

/**
 * The type Gender controller test.
 */
public class GenderControllerTest {
    /**
     * The Gender repository.
     */
    @Mock
    private GenderRepository genderRepository;

    /**
     * The Gender controller.
     */
    @InjectMocks
    private GenderController genderController;

    /**
     * The Expected genders.
     */
    private List<Gender> expectedGenders;


    /**
     * Test get all genders.
     */
    @Test
    void testGetAllGenders() {        // Initialisez vos données de test ici
        MockitoAnnotations.openMocks(this);
        // Initialisez vos données de test ici
        expectedGenders = Arrays.asList(new Gender(1, "Male"), new Gender(2, "Female"));
        given(genderRepository.findAll()).willReturn(expectedGenders);

        List<Gender> genders = genderController.getAll();

        // Vérifier que la liste retournée contient les genres attendus
        assertEquals(expectedGenders, genders);
    }

    /**
     * Test default constructor.
     */
    @Test
    void testDefaultConstructor() {
        Gender gender = new Gender();
        // Vérifier si l'objet gender est bien créé
        assertEquals(null, gender.getId()); // ou utiliser assertNull pour vérifier si l'id est null
        assertEquals(null, gender.getLabel()); // ou utiliser assertNull pour vérifier si le label est null
    }

    /**
     * Test constructor with parameters.
     */
    @Test
    void testConstructorWithParameters() {
        Integer id = 1;
        String label = "Male";
        Gender gender = new Gender(id, label);

        // Vérifier si les valeurs sont correctement affectées via le constructeur
        assertEquals(id, gender.getId());
        assertEquals(label, gender.getLabel());
    }

    /**
     * Test set and get id.
     */
    @Test
    void testSetAndGetId() {
        Gender gender = new Gender();
        Integer id = 2;
        gender.setId(id);

        // Vérifier si getId retourne la valeur correcte après avoir utilisé setId
        assertEquals(id, gender.getId());
    }

    /**
     * Test set and get label.
     */
    @Test
    void testSetAndGetLabel() {
        Gender gender = new Gender();
        String label = "Female";
        gender.setLabel(label);

        // Vérifier si getLabel retourne la valeur correcte après avoir utilisé setLabel
        assertEquals(label, gender.getLabel());
    }
}
