package com.example.devopslearning.config;

import java.time.Clock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Application configuration.
 *
 * Purpose:
 * - Provides injectable infrastructure beans (e.g., {@link Clock}) to make testing easy.
 */
@Configuration
public class AppConfig {
  @Bean
  public Clock clock() {
    return Clock.systemUTC();
  }
}

