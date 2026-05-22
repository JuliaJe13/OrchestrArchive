package de.dhbw.orchestrachive.orchestrarchive.model.repository;

import de.dhbw.orchestrachive.orchestrarchive.model.SheetMusic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SheetMusicRepository extends JpaRepository<SheetMusic, Long> {

    List<SheetMusic> findByTitleContainingIgnoreCase(String title);
    List<SheetMusic> findByComposerContainingIgnoreCase(String composer);
}