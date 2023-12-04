package com.jarspeed.api.repository;


import com.jarspeed.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface Repository pour l'entité User.
 * <p>
 * Cette interface étend JpaRepository, fournissant des méthodes CRUD pour
 * l'entité User. Elle peut également être utilisée pour définir des méthodes
 * de requête personnalisées.
 * </p>
 */
public interface UserRepository extends JpaRepository<User, Long> {
    // Méthodes personnalisées si nécessaire
}
