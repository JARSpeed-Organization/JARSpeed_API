package com.jarspeed.api.service;

import com.jarspeed.api.model.User;
import com.jarspeed.api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service pour la gestion des utilisateurs.
 * <p>
 * Ce service contient la logique métier liée aux utilisateurs et interagit
 * avec le UserRepository.
 * </p>
 */
@Service
public class UserService {
    /**
     * Référence au repository de
     * {@link com.jarspeed.api.repository.UserRepository}
     * pour l'accès et la manipulation des données des utilisateurs.
     * <p>
     * Ce repository est utilisé pour effectuer des opérations CRUD sur les
     * utilisateurs dans la base de données.
     * </p>
     */
    private UserRepository userRepository;

    /**
     * Récupère la liste de tous les points d'intérêts.
     * <p>
     * Cette méthode appelle le repository pour récupérer tous les utilisateurs
     * stockés dans la base de données.
     * </p>
     *
     * @return une liste de tous les utilisateurs.
     */
    public List<User> findAll() {
        return userRepository.findAll();
    }

    // Autres méthodes métier
}
