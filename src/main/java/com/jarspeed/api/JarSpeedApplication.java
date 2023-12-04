package com.jarspeed.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principale de l'application Spring Boot pour le système de
 * de parcours sportifs.
 * <p>
 * Cette classe lance l'application en utilisant Spring Boot, ce qui
 * configure automatiquement l'application basée sur les dépendances
 * ajoutées et les propriétés définies.
 * Elle sert de point d'entrée pour l'application.
 * </p>
 */
@SuppressWarnings("checkstyle:FinalClass")
@SpringBootApplication
public class JarSpeedApplication {

    /**
     * Constructeur privé pour empêcher l'instanciation.
     */
    private JarSpeedApplication() {
        // Constructeur privé pour empêcher l'instanciation
    }

    /**
     * Point d'entrée principal de l'application.
     *
     * @param args Arguments de la ligne de commande passés au démarrage
     * de l'application.
     */
    public static void main(final String[] args) {
        SpringApplication.run(JarSpeedApplication.class, args);
    }
}
