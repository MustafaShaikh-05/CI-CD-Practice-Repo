package com.example.devopslearning.repository;

import com.example.devopslearning.model.Note;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.springframework.stereotype.Repository;

/**
 * In-memory repository implementation.
 *
 * Purpose:
 * - Stores notes in an {@link ArrayList} as requested (no database).
 * - Uses a read/write lock to avoid obvious concurrency issues under parallel requests.
 */
@Repository
public class InMemoryNoteRepository implements NoteRepository {
  private final List<Note> notes = new ArrayList<>();
  private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

  @Override
  public List<Note> findAll() {
    lock.readLock().lock();
    try {
      return notes.stream()
          .sorted(Comparator.comparing(Note::getCreatedAt).reversed())
          .toList();
    } finally {
      lock.readLock().unlock();
    }
  }

  @Override
  public Optional<Note> findById(UUID id) {
    lock.readLock().lock();
    try {
      return notes.stream().filter(n -> n.getId().equals(id)).findFirst();
    } finally {
      lock.readLock().unlock();
    }
  }

  @Override
  public Note save(Note note) {
    lock.writeLock().lock();
    try {
      for (int i = 0; i < notes.size(); i++) {
        if (notes.get(i).getId().equals(note.getId())) {
          notes.set(i, note);
          return note;
        }
      }
      notes.add(note);
      return note;
    } finally {
      lock.writeLock().unlock();
    }
  }

  @Override
  public boolean deleteById(UUID id) {
    lock.writeLock().lock();
    try {
      return notes.removeIf(n -> n.getId().equals(id));
    } finally {
      lock.writeLock().unlock();
    }
  }
}

