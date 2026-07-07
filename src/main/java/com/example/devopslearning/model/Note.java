package com.example.devopslearning.model;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

/**
 * Domain model for a Note.
 *
 * Purpose:
 * - Represents the core business entity the API manages.
 * - Simple enough to store in memory, but shaped like a "real" model object.
 */
public final class Note {
  private final UUID id;
  private final String title;
  private final String content;
  private final Instant createdAt;
  private final Instant updatedAt;

  public Note(UUID id, String title, String content, Instant createdAt, Instant updatedAt) {
    this.id = Objects.requireNonNull(id, "id");
    this.title = Objects.requireNonNull(title, "title");
    this.content = Objects.requireNonNull(content, "content");
    this.createdAt = Objects.requireNonNull(createdAt, "createdAt");
    this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt");
  }

  public UUID getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getContent() {
    return content;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }
}

