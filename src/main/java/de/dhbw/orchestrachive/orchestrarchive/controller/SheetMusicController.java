package de.dhbw.orchestrachive.orchestrarchive.controller;

import de.dhbw.orchestrachive.orchestrarchive.model.DifficultyLevel;
import de.dhbw.orchestrachive.orchestrarchive.model.SheetMusic;
import de.dhbw.orchestrachive.orchestrarchive.service.SheetMusicService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/sheet-music")
public class SheetMusicController {

    private final SheetMusicService service;

    public SheetMusicController(SheetMusicService service) {
        this.service = service;
    }

    @GetMapping
    public List<SheetMusic> getAll() {
        return service.findAll();
    }
    @GetMapping("/search/title")
    public List<SheetMusic> findByTitle(@RequestParam String title) {
        return service.findByTitle(title);
    }
    @GetMapping("/search/composer")
    public List<SheetMusic> findByComposer(@RequestParam String composer) {
        return service.findByComposer(composer);
    }
    @GetMapping("/search/arranger")
    public List<SheetMusic> findByArranger(@RequestParam String arranger) {
        return service.findByArranger(arranger);
    }
    @GetMapping("/search/publisher")
    public List<SheetMusic> findByPublisher(@RequestParam String publisher) {
        return service.findByPublisher(publisher);
    }
    @GetMapping("/search/year")
    public List<SheetMusic> findByYear(@RequestParam int year) {
        return service.findByYear(year);
    }
    @GetMapping("/search/level")
    public List<SheetMusic> findByLevel(@RequestParam DifficultyLevel level) {
        return service.findByLevel(level);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SheetMusic> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SheetMusic> create(@Valid @RequestBody SheetMusic request) {
        SheetMusic created = service.create(request);
        return ResponseEntity
                .created(URI.create("/api/sheet-music/" + created.getId()))
                .body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SheetMusic> update(@PathVariable Long id, @Valid @RequestBody SheetMusic request) {
        return service.update(id, request)
                .map(ResponseEntity::ok)
                .orElse((ResponseEntity.notFound().build()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
