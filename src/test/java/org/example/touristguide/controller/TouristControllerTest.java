package org.example.touristguide.controller;

import org.example.touristguide.service.TouristService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TouristController.class)

class TouristControllerTest {

   @Autowired
   private MockMvc mockMvc;

   @MockitoBean
   private TouristService touristService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getAttractions() {
    }

    @Test
    void showTags() {
    }

    @Test
    void addAttraction() throws Exception {
        mockMvc.perform(post("/attractions/add")
                .param("attraction name", "attraction value")
                .param("another attraction name", "another attraction value")
                .param("last attraction name", "last attraction value"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/attractions"));

    }

    @Test
    void getAttractionsByName() {
    }

    @Test
    void editAttraction() {
    }

    @Test
    void updateAttraction() {
    }

    @Test
    void deleteAttraction() {
    }
}