package de.dhbw.orchestrachive.orchestrarchive.controller;

import de.dhbw.orchestrachive.orchestrarchive.model.VoicePart;
import de.dhbw.orchestrachive.orchestrarchive.service.VoicePartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/voice-parts")
public class VoicePartController {

    private final VoicePartService service;

    public VoicePartController(VoicePartService service) {
        this.service = service;
    }

    @GetMapping
    public List<VoicePart> getAll() {
        return service.findAll();
    }
    @GetMapping("/search/instrument")
    public List<VoicePart> findByInstrument(@RequestParam String instrument) {
        return service.findByInstrument(instrument);
    }
    @GetMapping("/search/instrument-group")
    public List<VoicePart> findByInstrumentGroup(@RequestParam String instrumentgroup) {
        return service.findByInstrumentGroup(instrumentgroup);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VoicePart> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<VoicePart> create(@RequestBody VoicePart request) {
        VoicePart created = service.create(request);
        return ResponseEntity
                .created(URI.create("/api/voice-parts/" + created.getId()))
                .body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VoicePart> update(@PathVariable Long id, @RequestBody VoicePart request) {
        return service.update(id, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
