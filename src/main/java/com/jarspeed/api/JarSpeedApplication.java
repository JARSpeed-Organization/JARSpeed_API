package com.jarspeed.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 */
@SpringBootApplication
public class JarSpeedApplication {

    /**
     *
     */
    private JarSpeedApplication() { }

    /**
     *
     * @param args test
     */
    public static void main(final String[] args) {
        SpringApplication.run(JarSpeedApplication.class, args);
    }

}
