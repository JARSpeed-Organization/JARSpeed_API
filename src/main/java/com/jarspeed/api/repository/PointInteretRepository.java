package com.jarspeed.api.repository;


import com.jarspeed.api.model.PointInteret;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface Repository pour l'entité PointInteret.
 * <p>
 * Cette interface étend JpaRepository, fournissant des méthodes CRUD
 * pour l'entité PointInteret. Elle peut également être utilisée pour
 * définir des méthodes de requête personnalisées.
 * </p>
 */
public interface PointInteretRepository extends
        JpaRepository<PointInteret, Long> {
    // Méthodes personnalisées si nécessaire
}
