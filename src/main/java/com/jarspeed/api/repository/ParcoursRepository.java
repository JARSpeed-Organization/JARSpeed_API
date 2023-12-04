package com.jarspeed.api.repository;

import com.jarspeed.api.model.Parcours;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface Repository pour l'entité Parcours.
 * <p>
 * Cette interface étend JpaRepository, fournissant des méthodes CRUD pour
 * l'entité Parcours. Elle peut également être utilisée pour définir des
 * méthodes de requête personnalisées.
 * </p>
 */
public interface ParcoursRepository extends JpaRepository<Parcours, Long> {
    // Méthodes personnalisées si nécessaire
}
