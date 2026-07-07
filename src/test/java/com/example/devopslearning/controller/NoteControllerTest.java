package com.example.devopslearning.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Lightweight controller test using Spring's {@link MockMvc}.
 *
 * Purpose:
 * - Demonstrates CI-friendly tests that validate the HTTP API without running a real server.
 */
@SpringBootTest
@AutoConfigureMockMvc
class NoteControllerTest {
  @Autowired private MockMvc mvc;
  @Autowired private ObjectMapper objectMapper;

  @Test
  void createThenList() throws Exception {
    String body = objectMapper.writeValueAsString(Map.of("title", "T", "content", "C"));

    mvc.perform(post("/api/notes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(body))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.title", is("T")))
        .andExpect(jsonPath("$.content", is("C")));

    mvc.perform(get("/api/notes"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].title", is("T")));
  }
}

