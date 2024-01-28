package com.jarspeed.api.gender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for gender management.
 * <p>
 * This controller handles HTTP requests for operations on.
 * users, such as retrieving, creating, updating and deleting
 * users.
 * </p>
 */
@RestController
@RequestMapping("/genders")
public class GenderController {

    /**
     * Gender repository.
     */
    @Autowired
    private GenderRepository genderRepository;

    /**
     * Recover all gender in database.
     * @return All gender
     */
    @GetMapping("/")
    public List<Gender> getAll() {
        return genderRepository.findAll();
    }
}
