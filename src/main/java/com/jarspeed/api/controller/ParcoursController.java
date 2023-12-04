package com.jarspeed.api.controller;

import com.jarspeed.api.model.Parcours;
import com.jarspeed.api.service.ParcoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Contrôleur REST pour la gestion des parcours sportifs.
 * <p>
 * Ce contrôleur gère les requêtes HTTP pour les opérations sur les parcours,
 * comme récupérer, créer, mettre à jour et supprimer des parcours.
 * </p>
 */
@RestController
@RequestMapping("/parcours")
public class ParcoursController {
// Injection de dépendance pour le service Parcours
    /**
     * Service pour gérer les opérations liées aux parcours.
     * Le framework Spring injecte automatiquement une instance de
     * ParcoursService.
     */
    @Autowired
    private ParcoursService parcoursService;

    /**
     * Récupère et renvoie une liste de tous les parcours.
     * Cette méthode gère les requêtes GET pour obtenir une liste de tous
     * les parcours.
     * Elle utilise le ParcoursService pour interroger la base de données et
     * renvoie la liste des parcours.
     *
     * @return La liste des parcours disponibles dans la base de données.
     */
    @GetMapping
    public List<Parcours> getAllParcours() {
        return parcoursService.findAll();
    }

    // Autres points de terminaison
}
