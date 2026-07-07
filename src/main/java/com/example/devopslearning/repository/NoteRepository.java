package com.example.devopslearning.repository;

import com.example.devopslearning.model.Note;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository abstraction for Notes.
 *
 * Purpose:
 * - Mirrors a typical data-access layer in real projects.
 * - Lets you later swap in a real database without changing controllers/services much.
 */
public interface NoteRepository {
  List<Note> findAll();

  Optional<Note> findById(UUID id);

  Note save(Note note);

  boolean deleteById(UUID id);
}

