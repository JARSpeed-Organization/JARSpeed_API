package com.jarspeed.api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Get tous les utilisateurs
    @GetMapping
    public String getAllUsers() {
        return "tesssst";
        //return userRepository.findAll();
    }



}
