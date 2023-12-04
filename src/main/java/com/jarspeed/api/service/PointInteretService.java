package com.jarspeed.api.service;

import com.jarspeed.api.model.PointInteret;
import com.jarspeed.api.repository.PointInteretRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service pour la gestion des points d'intérêt des parcours sportifs.
 * <p>
 * Ce service contient la logique métier liée aux points d'intérêt et
 * interagit avec le PointInteretRepository.
 * </p>
 */
@Service
public class PointInteretService {
    /**
     * Référence au repository de
     * {@link com.jarspeed.api.repository.PointInteretRepository}
     * pour l'accès et la manipulation des données de parcours.
     * <p>
     * Ce repository est utilisé pour effectuer des opérations CRUD
     * sur les points d'intérêts dans la base de données.
     * </p>
     */
    private PointInteretRepository pointInteretRepository;

    /**
     * Récupère la liste de tous les points d'intérêts.
     * <p>
     * Cette méthode appelle le repository pour récupérer tous les points
     * d'intérêts stockés dans la base de données.
     * </p>
     *
     * @return une liste de tous les points d'intérêts.
     */
    public List<PointInteret> findAll() {
        return pointInteretRepository.findAll();
    }

    // Autres méthodes métier
}
