package com.jarspeed.api.service;

import com.jarspeed.api.model.Parcours;
import com.jarspeed.api.repository.ParcoursRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service pour la gestion des parcours sportifs.
 * <p>
 * Ce service contient la logique métier liée aux parcours et interagit
 * avec le ParcoursRepository.
 * </p>
 */
@Service
public class ParcoursService {

    /**
     * Référence au repository de
     * {@link com.jarspeed.api.repository.ParcoursRepository}
     * pour l'accès et la manipulation des données de parcours.
     * <p>
     * Ce repository est utilisé pour effectuer des opérations CRUD sur les
     * parcours dans la base de données.
     * </p>
     */
    private ParcoursRepository parcoursRepository;

    /**
     * Récupère la liste de tous les parcours.
     * <p>
     * Cette méthode appelle le repository pour récupérer tous les parcours
     * stockés dans la base de données.
     * </p>
     *
     * @return une liste de tous les parcours.
     */
    public List<Parcours> findAll() {
        return parcoursRepository.findAll();
    }

    // Autres méthodes métier
}
