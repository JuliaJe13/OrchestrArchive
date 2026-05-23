package de.dhbw.orchestrachive.orchestrarchive.model.repository;

import de.dhbw.orchestrachive.orchestrarchive.model.VoicePart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoicePartRepository extends JpaRepository<VoicePart, Long> {

    List<VoicePart> findByInstrumentContainingIgnoreCase(String instrument);
    List<VoicePart> findByInstrumentGroupContainingIgnoreCase(String instrumentGroup);
}