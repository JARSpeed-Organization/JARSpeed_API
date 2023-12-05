package com.jarspeed.api.controller;

import com.jarspeed.api.model.User;
import com.jarspeed.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Contrôleur REST pour la gestion des utilisateurs.
 * <p>
 * Ce contrôleur gère les requêtes HTTP pour les opérations sur
 * les utilisateurs, comme récupérer, créer, mettre à jour et supprimer
 * des utilisateurs.
 * </p>
 */
@RestController
@RequestMapping("/users")
public class UserController {

    // Injection de dépendance pour le service User
    /**
     * Service pour gérer les opérations liées aux utilisateurs.
     * Le framework Spring injecte automatiquement une instance de UserService.
     */
    @Autowired
    private UserService userService;
    // Autres points de terminaison
}
