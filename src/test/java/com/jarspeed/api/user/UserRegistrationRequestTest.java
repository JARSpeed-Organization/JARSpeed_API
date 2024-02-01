package com.jarspeed.api.user;

import com.jarspeed.api.gender.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The type User registration request test.
 */
class UserRegistrationRequestTest {

    /**
     * The Request.
     */
    private UserRegistrationRequest request;
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
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        // Initialiser les données de test communes
        lastname = "Doe";
        firstname = "John";
        email = "john@example.com";
        password = "securePassword";


        // Initialiser l'objet UserRegistrationRequest pour les tests de getters/setters
        request = new UserRegistrationRequest();
        request.setLastname(lastname);
        request.setFirstname(firstname);
        request.setEmail(email);
        request.setPassword(password);

    }

    /**
     * Test user registration request setters and getters.
     */
    @Test
    void testUserRegistrationRequestSettersAndGetters() {
        // Validation via les getters
        assertEquals(lastname, request.getLastname());
        assertEquals(firstname, request.getFirstname());
        assertEquals(email, request.getEmail());
        assertEquals(password, request.getPassword());
    }

    /**
     * Test user registration request constructor.
     */
    @Test
    void testUserRegistrationRequestConstructor() {
        // Réinitialiser request avec le constructeur
        request = new UserRegistrationRequest(lastname, firstname, email, password);

        // Validation
        assertEquals(lastname, request.getLastname());
        assertEquals(firstname, request.getFirstname());
        assertEquals(email, request.getEmail());
        assertEquals(password, request.getPassword());
    }

    /**
     * Test to string method.
     */
    @Test
    void testToStringMethod() {
        // Réinitialiser request pour ce test spécifique si nécessaire
        request = new UserRegistrationRequest(lastname, firstname, email, "password");

        // Construction de la chaîne attendue
        String expected = "UserRegistrationRequest{" +
                "lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + "[PROTECTED]" + '\'' +
                '}';

        // Vérification que la sortie de toString() correspond à ce qui est attendu
        assertEquals(expected, request.toString());
    }
}
