package de.dhbw.orchestrachive.orchestrarchive.service;

import de.dhbw.orchestrachive.orchestrarchive.model.Category;
import de.dhbw.orchestrachive.orchestrarchive.model.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<Category> findAll() {
        return repository.findAll();
    }
    public Optional<Category> findById(Long id) {
        return repository.findById(id);
    }
    public Category create(Category entity) {
        return repository.save(entity);
    }
    public Optional<Category> update(Long id, Category updated) {
        return repository.findById(id).map(existing -> {
            existing.setGenre(updated.getGenre());
            existing.setDescription(updated.getDescription());
            return repository.save(existing);
        });
    }
    public boolean delete(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}
