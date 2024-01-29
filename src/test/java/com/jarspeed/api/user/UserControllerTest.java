package com.jarspeed.api.user;

import com.jarspeed.api.gender.Gender;
import com.jarspeed.api.gender.GenderController;
import com.jarspeed.api.gender.GenderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    private final Gender HOMME = new Gender(1, "Homme");
    private final List<User> USERS = List.of(new User(1, "McGregor", "Conor",
     "conor.mcgregor@gmail.com", 35, HOMME, 71.0, "password"), new User(2,
            "Saint Denis", "Benoît",
            "benoit.saintdenis@gmail.com", 28, HOMME, 70.3, "password"));

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserController userController;

    @Test
    void getAll() {
        when(userRepository.findAll()).thenReturn(USERS);
        assertEquals(USERS, userController.getAll());
    }

    @Test
    void findUserById() {
        when(userRepository.findUserById(USERS.get(0).getId())).thenReturn(USERS.get(0));
        assertEquals(USERS.get(0),
                userController.findUserById(USERS.get(0).getId()));
    }

    @Test
    void mergeUserEmailChanged() {
        User existingUser = new User(1, "McGregor", "Conor",
                "conor.mcgregor@gmail.com", 35, HOMME, 71.0, "password");
        User newUser = new User(1,"Saint Denis", "Benoît",
                "benoit.saintdenis@gmail.com", 28, HOMME, null, null);

        when(userRepository.findUserById(anyInt())).thenReturn(existingUser);
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArguments()[0]);

        User mergedUser = userController.merge(newUser);

        verify(userRepository).save(any(User.class));
        assertEquals(newUser.getLastname(), mergedUser.getLastname());
        assertEquals(newUser.getFirstname(), mergedUser.getFirstname());
        assertEquals(newUser.getEmail(), mergedUser.getEmail());
        assertEquals(newUser.getAge(), mergedUser.getAge());
        assertEquals(newUser.getGender(), mergedUser.getGender());
        assertEquals(newUser.getWeight(), mergedUser.getWeight());
        assertEquals(newUser.getPassword(), mergedUser.getPassword());
    }
}