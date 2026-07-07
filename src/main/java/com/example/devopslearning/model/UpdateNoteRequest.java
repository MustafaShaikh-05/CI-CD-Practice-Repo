package com.example.devopslearning.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * API request body for updating a note.
 *
 * Purpose:
 * - Clear contract for PUT operations.
 * - Avoids partial-update complexity (no PATCH in this learning project).
 */
public record UpdateNoteRequest(
    @NotBlank @Size(max = 80) String title,
    @NotBlank @Size(max = 2000) String content
) {}

