package org.example.touristguide.controller;

import org.example.touristguide.model.City;
import org.example.touristguide.model.Tag;
import org.example.touristguide.model.TouristAttraction;
import org.example.touristguide.service.TouristService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(TouristController.class)
class TouristControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TouristService touristService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAttractions () throws Exception {
        mockMvc.perform(get("/attractions"))
                .andExpect(status().isOk())
                .andExpect(view().name("attraction-list"));


    }

    @Test
    void showTags() throws Exception {
        String name = "Tivoli"; //bedre at lave objektet i setUp metoden på test klassen
        TouristAttraction tivoli = new TouristAttraction("Tivoli",
                "En historisk forlystelsespark i hjertet af København.",
                City.KØBENHAVN,
                Arrays.asList(Tag.FORLYSTELSESPARK, Tag.RESTAURANT));

        when(touristService.getAttractionByName(name)).thenReturn(tivoli);
        mockMvc.perform(get("/attractions/{name}/tags", name))
                .andExpect(status().isOk())
                .andExpect(view().name("tags"));

    }




}