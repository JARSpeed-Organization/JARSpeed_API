package com.jarspeed.api.gender;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Gender repository.
 */
public interface GenderRepository extends JpaRepository<Gender, Integer> {
}
