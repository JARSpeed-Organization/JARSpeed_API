package com.jarspeed.api.gender;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Gender repository.
 */
public interface GenderRepository extends JpaRepository<Gender, Integer> {
    /**
     * Find gender by id gender.
     *
     * @param pId the p id
     * @return the gender
     */
    Gender findGenderById(Integer pId);
}
