package de.dhbw.orchestrachive.orchestrarchive.service;

import de.dhbw.orchestrachive.orchestrarchive.model.VoicePart;
import de.dhbw.orchestrachive.orchestrarchive.model.repository.VoicePartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoicePartService {

    private final VoicePartRepository repository;

    public VoicePartService(VoicePartRepository repository) {
        this.repository = repository;
    }

    public List<VoicePart> findAll() {
        return repository.findAll();
    }
    public Optional<VoicePart> findById(Long id) {
        return repository.findById(id);
    }
    public List<VoicePart> findByInstrument(String instrument) {
        return repository.findByInstrumentContainingIgnoreCase(instrument);
    }
    public List<VoicePart> findByInstrumentGroup(String instrumentGroup) {
        return repository.findByInstrumentGroupContainingIgnoreCase(instrumentGroup);
    }
    public VoicePart create(VoicePart entity) {
        return repository.save(entity);
    }
    public Optional<VoicePart> update(Long id, VoicePart updated) {
        return repository.findById(id).map(existing -> {
            existing.setInstrument(updated.getInstrument());
            existing.setInstrumentGroup(updated.getInstrumentGroup());
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
