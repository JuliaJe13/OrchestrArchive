package de.dhbw.orchestrachive.orchestrarchive.service;

import de.dhbw.orchestrachive.orchestrarchive.model.DifficultyLevel;
import de.dhbw.orchestrachive.orchestrarchive.model.SheetMusic;
import de.dhbw.orchestrachive.orchestrarchive.model.repository.SheetMusicRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SheetMusicService {

    private final SheetMusicRepository repository;

    public SheetMusicService(SheetMusicRepository repository) {
        this.repository = repository;
    }

    public List<SheetMusic> findAll() {
        return repository.findAll();
    }
    public Optional<SheetMusic> findById(Long id) {
        return repository.findById(id);
    }
    public List<SheetMusic> findByTitle(String title) {
        return repository.findByTitleContainingIgnoreCase(title);
    }
    public List<SheetMusic> findByComposer(String composer) {
        return repository.findByComposerContainingIgnoreCase(composer);
    }
    public List<SheetMusic> findByArranger(String arranger) {
        return repository.findByArrangerContainingIgnoreCase(arranger);
    }
    public List<SheetMusic> findByYear(int year) {
        return repository.findByYear(year);
    }
    public List<SheetMusic> findByPublisher(String publisher) {
        return repository.findByPublisherContainingIgnoreCase(publisher);
    }
    public List<SheetMusic> findByLevel(DifficultyLevel level) {
        return repository.findByLevel(level);
    }

    public SheetMusic create(SheetMusic entity) {
        return repository.save(entity);
    }
    public Optional<SheetMusic> update(Long id, SheetMusic updated) {
        return repository.findById(id).map(existing -> {
            existing.setTitle(updated.getTitle());
            existing.setComposer(updated.getComposer());
            existing.setArranger(updated.getArranger());
            existing.setYear(updated.getYear());
            existing.setPublisher(updated.getPublisher());
            existing.setLevel(updated.getLevel());
            existing.setCategories(updated.getCategories());
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
