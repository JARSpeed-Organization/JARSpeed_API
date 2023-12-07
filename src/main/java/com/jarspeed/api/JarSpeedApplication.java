package com.jarspeed.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Main class of the Spring Boot application for the
 * system.
 * <p>
 * This class launches the application using Spring Boot, which
 * automatically configures the application based on the dependencies
 * dependencies and defined properties.
 * It serves as the entry point for the application.
 * </p>
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class JarSpeedApplication {

    /**
     * Private constructor to prevent instantiation.
     */
    public JarSpeedApplication() {

    }

    /**
     * Application's main entry point.
     *
     * @param args Command line arguments passed at application startup.
     * of the application.
     */
    public static void main(final String[] args) {
        SpringApplication.run(JarSpeedApplication.class, args);
    }
}
