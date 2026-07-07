package com.example.devopslearning.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.devopslearning.model.CreateNoteRequest;
import com.example.devopslearning.model.Note;
import com.example.devopslearning.model.UpdateNoteRequest;
import com.example.devopslearning.repository.InMemoryNoteRepository;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link NoteService}.
 *
 * Purpose:
 * - Demonstrates fast unit testing of business logic (no Spring context required).
 */
class NoteServiceTest {
  @Test
  void createAndGetNote() {
    Clock fixed = Clock.fixed(Instant.parse("2026-01-01T00:00:00Z"), ZoneOffset.UTC);
    NoteService service = new NoteService(new InMemoryNoteRepository(), fixed);

    Note created = service.create(new CreateNoteRequest("Title", "Content"));
    assertNotNull(created.getId());
    assertEquals("Title", created.getTitle());
    assertEquals("Content", created.getContent());

    Note fetched = service.getNote(created.getId());
    assertEquals(created.getId(), fetched.getId());
    assertEquals(Instant.parse("2026-01-01T00:00:00Z"), fetched.getCreatedAt());
    assertEquals(Instant.parse("2026-01-01T00:00:00Z"), fetched.getUpdatedAt());
  }

  @Test
  void updateChangesUpdatedAtButKeepsCreatedAt() {
    Clock t1 = Clock.fixed(Instant.parse("2026-01-01T00:00:00Z"), ZoneOffset.UTC);
    InMemoryNoteRepository repo = new InMemoryNoteRepository();
    NoteService service = new NoteService(repo, t1);

    Note created = service.create(new CreateNoteRequest("A", "B"));

    Clock t2 = Clock.fixed(Instant.parse("2026-01-02T00:00:00Z"), ZoneOffset.UTC);
    NoteService serviceAtT2 = new NoteService(repo, t2);

    Note updated = serviceAtT2.update(created.getId(), new UpdateNoteRequest("A2", "B2"));
    assertEquals(created.getId(), updated.getId());
    assertEquals(Instant.parse("2026-01-01T00:00:00Z"), updated.getCreatedAt());
    assertEquals(Instant.parse("2026-01-02T00:00:00Z"), updated.getUpdatedAt());
  }

  @Test
  void deleteUnknownNoteThrowsNotFound() {
    Clock fixed = Clock.fixed(Instant.parse("2026-01-01T00:00:00Z"), ZoneOffset.UTC);
    NoteService service = new NoteService(new InMemoryNoteRepository(), fixed);

    assertThrows(NotFoundException.class, () -> service.delete(java.util.UUID.randomUUID()));
  }
}

