package com.example.devopslearning.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * API request body for creating a note.
 *
 * Purpose:
 * - Keeps HTTP payload shapes separate from the domain model.
 * - Validation here produces clear 400 responses without extra code.
 */
public record CreateNoteRequest(
    @NotBlank @Size(max = 80) String title,
    @NotBlank @Size(max = 2000) String content
) {}

