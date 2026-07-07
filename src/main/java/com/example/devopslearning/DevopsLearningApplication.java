package com.example.devopslearning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application entry point.
 *
 * Purpose:
 * - Keeps bootstrapping in one obvious class.
 * - Enables component scanning for the {@code com.example.devopslearning} package.
 */
@SpringBootApplication
public class DevopsLearningApplication {
  public static void main(String[] args) {
    SpringApplication.run(DevopsLearningApplication.class, args);
  }
}

