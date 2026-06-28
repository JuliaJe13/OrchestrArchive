package de.dhbw.orchestrachive.orchestrarchive;

import de.dhbw.orchestrachive.orchestrarchive.model.Category;
import de.dhbw.orchestrachive.orchestrarchive.model.repository.CategoryRepository;
import de.dhbw.orchestrachive.orchestrarchive.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Mock
    CategoryRepository repository;

    @InjectMocks
    CategoryService service;

    @Test
    void shouldReturnAllCategories() {
        // Mock vorbereiten
        when(repository.findAll()).thenReturn(List.of(new Category("Marsch", "")));

        //Service aufrufen
        List<Category> result = service.findAll();

        //Ergebnis prüfen
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getGenre()).isEqualTo("Marsch");
    }

    @Test
    void shouldFindCategoryById() {
        // Mock vorbereiten
        when(repository.findById(1L)).thenReturn(Optional.of(new Category("Marsch", "")));

        //Service aufrufen
        Optional<Category> result = service.findById(1L);

        //Ergebnis prüfen
        assertThat(result).isPresent();
        assertThat(result.get().getGenre()).isEqualTo("Marsch");
    }
    @Test
    void shouldSaveCategory() {
        // Mock vorbereiten
        when(repository.save(any())).thenReturn(new Category("Marsch", ""));

        //Service aufrufen
        Category result = service.create(new Category("Marsch", ""));

        //Ergebnis prüfen
        assertThat(result.getGenre()).isEqualTo("Marsch");
    }
    @Test
    void shouldReturnEmptyWhenCategoryNotFound() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        Optional<Category> result = service.findById(99L);

        assertThat(result).isEmpty();
    }
    @Test
    void shouldDeleteCategory() {
        when(repository.existsById(1L)).thenReturn(true);

        boolean result = service.delete(1L);

        assertThat(result).isTrue();
    }

    @Test
    void shouldUpdateCategory() {
        // Arrange
        Category existing = new Category("Marsch", "Alt");
        Category updated = new Category("Marsch", "Neu");

        when(repository.findById(1L)).thenReturn(Optional.of(existing));
        when(repository.save(any())).thenReturn(updated);

        Optional<Category> result = service.update(1L, updated);

        assertThat(result).isPresent();
        assertThat(result.get().getDescription()).isEqualTo("Neu");
    }

}