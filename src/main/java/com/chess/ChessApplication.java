package com.chess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class for launching the Chess Game Spring Boot application.
 * This class contains the entry point for the application.
 */
@SpringBootApplication // Enables component scanning and auto-configuration
public class ChessApplication {

    /**
     * Main method that starts the Spring Boot application.
     * @param args Command-line arguments (not used here).
     */
    public static void main(String[] args) {
        SpringApplication.run(ChessApplication.class, args);
    }
}

