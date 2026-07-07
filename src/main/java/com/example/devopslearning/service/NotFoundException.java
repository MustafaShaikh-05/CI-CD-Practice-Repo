package com.example.devopslearning.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Domain-level "not found" exception.
 *
 * Purpose:
 * - Keeps controller code clean (throw exception, let Spring translate to 404).
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
  public NotFoundException(String message) {
    super(message);
  }
}

