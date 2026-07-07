package com.example.devopslearning.controller;

import com.example.devopslearning.model.CreateNoteRequest;
import com.example.devopslearning.model.Note;
import com.example.devopslearning.model.UpdateNoteRequest;
import com.example.devopslearning.service.NoteService;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * REST controller for notes.
 *
 * Purpose:
 * - Defines the HTTP API contract.
 * - Delegates all business logic to {@link NoteService}.
 */
@RestController
@RequestMapping("/api/notes")
public class NoteController {
  private final NoteService service;

  public NoteController(NoteService service) {
    this.service = service;
  }

  @GetMapping
  public List<Note> list() {
    return service.listNotes();
  }

  @GetMapping("/{id}")
  public Note get(@PathVariable UUID id) {
    return service.getNote(id);
  }

  @PostMapping
  public ResponseEntity<Note> create(@Valid @RequestBody CreateNoteRequest request) {
    Note created = service.create(request);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(created.getId())
        .toUri();
    return ResponseEntity.created(location).body(created);
  }

  @PutMapping("/{id}")
  public Note update(@PathVariable UUID id, @Valid @RequestBody UpdateNoteRequest request) {
    return service.update(id, request);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable UUID id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}

