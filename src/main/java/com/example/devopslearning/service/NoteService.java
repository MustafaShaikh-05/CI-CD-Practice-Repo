package com.example.devopslearning.service;

import com.example.devopslearning.model.CreateNoteRequest;
import com.example.devopslearning.model.Note;
import com.example.devopslearning.model.UpdateNoteRequest;
import com.example.devopslearning.repository.NoteRepository;
import java.time.Clock;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

/**
 * Business logic layer for notes.
 *
 * Purpose:
 * - Central place for business rules (even though the rules are simple for now).
 * - Keeps controllers thin and easy to test.
 */
@Service
public class NoteService {
  private final NoteRepository repository;
  private final Clock clock;

  public NoteService(NoteRepository repository, Clock clock) {
    this.repository = repository;
    this.clock = clock;
  }

  public List<Note> listNotes() {
    return repository.findAll();
  }

  public Note getNote(UUID id) {
    return repository.findById(id)
        .orElseThrow(() -> new NotFoundException("Note not found: " + id));
  }

  public Note create(CreateNoteRequest request) {
    Instant now = Instant.now(clock);
    Note note = new Note(UUID.randomUUID(), request.title(), request.content(), now, now);
    return repository.save(note);
  }

  public Note update(UUID id, UpdateNoteRequest request) {
    Note existing = getNote(id);
    Note updated = new Note(existing.getId(), request.title(), request.content(), existing.getCreatedAt(), Instant.now(clock));
    return repository.save(updated);
  }

  public void delete(UUID id) {
    boolean deleted = repository.deleteById(id);
    if (!deleted) {
      throw new NotFoundException("Note not found: " + id);
    }
  }
}

