package de.dhbw.orchestrachive.orchestrarchive;

import de.dhbw.orchestrachive.orchestrarchive.controller.CategoryController;
import de.dhbw.orchestrachive.orchestrarchive.model.Category;
import de.dhbw.orchestrachive.orchestrarchive.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import java.util.Optional;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    CategoryService service;

    @Test
    void shouldReturnAllCategories() throws Exception {
        // Should be returned
        when(service.findAll()).thenReturn(List.of(new Category("Marsch", "")));

        // Send HTTP-Request
        mockMvc.perform(get("/api/categories"))
                // Check Answer
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].genre").value("Marsch"));
    }

    @Test
    void shouldReturn404WhenCategoryNotFound() throws Exception {
        when(service.findById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/categories/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCategoryById() throws Exception {
        when(service.findById(1L)).thenReturn(Optional.of(new Category("Marsch", "")));

        mockMvc.perform(get("/api/categories/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.genre").value("Marsch"));
    }

    @Test
    void shouldReturn400WhenGenreIsBlank() throws Exception {
        mockMvc.perform(post("/api/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"genre\": \"\", \"description\": \"\"}"))
                .andExpect(status().isBadRequest());
    }
}
