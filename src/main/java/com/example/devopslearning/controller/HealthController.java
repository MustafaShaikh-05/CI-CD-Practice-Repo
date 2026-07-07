package com.example.devopslearning.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Simple health endpoint.
 *
 * Purpose:
 * - Useful for Docker/Kubernetes readiness checks later.
 * - Avoids pulling in Spring Actuator to keep this learning project simple.
 */
@RestController
@RequestMapping(path = "/health", produces = MediaType.TEXT_PLAIN_VALUE)
public class HealthController {
  private final String greetingMessage;

  public HealthController(@Value("${app.greetingMessage}") String greetingMessage) {
    this.greetingMessage = greetingMessage;
  }

  @GetMapping
  public String health() {
    return "OK - " + greetingMessage;
  }
}

