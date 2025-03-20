package org.example.touristguide.controller;

import org.example.touristguide.model.City;
import org.example.touristguide.model.Tag;
import org.example.touristguide.model.TouristAttraction;
import org.example.touristguide.service.TouristService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TouristController.class)
class TouristControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TouristService touristService;

    private TouristAttraction tivoli;

    @BeforeEach
    void setUp() {
        tivoli = new TouristAttraction("Tivoli",
                "En historisk forlystelsespark i hjertet af København.",
                City.KØBENHAVN,
                List.of(Tag.FORLYSTELSESPARK, Tag.RESTAURANT));
    }

    @Test
    void testGetAttractions() throws Exception {
        when(touristService.getAttractions()).thenReturn(List.of(tivoli));

        mockMvc.perform(get("/attractions"))
                .andExpect(status().isOk())
                .andExpect(view().name("attraction-list"))
                .andExpect(model().attributeExists("attractions"))
                .andExpect(model().attribute("attractions", List.of(tivoli)));
    }

    @Test
    void testGetAttractionByName() throws Exception {
        when(touristService.getAttractionByName("Tivoli")).thenReturn(tivoli);

        mockMvc.perform(get("/attractions/Tivoli"))
                .andExpect(status().isOk())
                .andExpect(view().name("attraction-details"))
                .andExpect(model().attributeExists("attraction"))
                .andExpect(model().attribute("attraction", tivoli));
    }

    @Test
    void testShowTags() throws Exception {
        when(touristService.getAttractionByName("Tivoli")).thenReturn(tivoli);

        mockMvc.perform(get("/attractions/Tivoli/tags"))
                .andExpect(status().isOk())
                .andExpect(view().name("tags"))
                .andExpect(model().attributeExists("attraction"))
                .andExpect(model().attribute("attraction", tivoli));
    }

    @Test
    void testSaveAttraction_Success() throws Exception {
        doNothing().when(touristService).saveAttraction(any(TouristAttraction.class));

        mockMvc.perform(post("/attractions/save")
                        .flashAttr("touristAttraction", tivoli))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/attractions"));
    }

    @Test
    void testSaveAttraction_Duplicate() throws Exception {
        doThrow(new IllegalArgumentException("Attraction already exists")).when(touristService).saveAttraction(any(TouristAttraction.class));

        mockMvc.perform(post("/attractions/save")
                        .flashAttr("touristAttraction", new TouristAttraction("Tivoli", "Desc", City.KØBENHAVN, List.of(Tag.FORLYSTELSESPARK))))
                .andExpect(status().isOk())
                .andExpect(view().name("error"))
                .andExpect(model().attributeExists("errorMessage"))
                .andExpect(model().attribute("errorMessage", "Attraction already exists"));
    }

    @Test
    void testEditAttraction() throws Exception {
        when(touristService.getAttractionByName("Tivoli")).thenReturn(tivoli);

        mockMvc.perform(get("/attractions/Tivoli/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("attraction-form"))
                .andExpect(model().attributeExists("touristAttraction", "cities", "tags"))
                .andExpect(model().attribute("touristAttraction", tivoli));
    }

    @Test
    void testUpdateAttraction() throws Exception {
        doNothing().when(touristService).updateAttraction(any(TouristAttraction.class));

        mockMvc.perform(post("/attractions/update")
                        .flashAttr("touristAttraction", tivoli))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/attractions"));
    }

    @Test
    void testDeleteAttraction() throws Exception {
        when(touristService.deleteAttraction("Tivoli")).thenReturn(tivoli);

        mockMvc.perform(post("/attractions/delete/Tivoli"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/attractions"));
    }

    @Test
    void testDeleteAttraction_NotFound() throws Exception {
        when(touristService.deleteAttraction("Invalid")).thenReturn(null);

        mockMvc.perform(post("/attractions/delete/Invalid"))
                .andExpect(status().isOk())  // Expecting HTTP 200 instead of 500
                .andExpect(view().name("error"))
                .andExpect(model().attributeExists("errorMessage"))
                .andExpect(model().attribute("errorMessage", "Ugyldig attraktion"));
    }
}