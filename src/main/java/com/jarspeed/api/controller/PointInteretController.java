package com.jarspeed.api.controller;

import com.jarspeed.api.model.PointInteret;
import com.jarspeed.api.service.PointInteretService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Contrôleur REST pour la gestion des points d'intérêt des parcours sportifs.
 * <p>
 * Ce contrôleur gère les requêtes HTTP pour les opérations sur les points
 * d'intérêt, comme récupérer, créer, mettre à jour et supprimer des points
 * d'intérêt.
 * </p>
 */
@RestController
@RequestMapping("/pointsInteret")
public class PointInteretController {
    // Injection de dépendance pour le service PointInteret
    /**
     * Service pour gérer les opérations liées aux points d'intérêt.
     * Le framework Spring injecte automatiquement une instance de
     * PointInteretService.
     */
    @Autowired
    private PointInteretService pointInteretService;

    /**
     * Récupère et renvoie une liste de tous les points d'intérêt.
     *
     * Cette méthode gère les requêtes GET pour obtenir une liste de tous
     * les points d'intérêt.
     * Elle utilise le PointInteretService pour interroger la base de données
     * et renvoie la liste des points d'intérêt.
     *
     * @return La liste des points d'intérêt disponibles dans la base de
     * données.
     */
    @GetMapping
    public List<PointInteret> getAllPointsInteret() {
        return pointInteretService.findAll();
    }

    // Autres points de terminaison
}
