package de.dhbw.orchestrachive.orchestrarchive.controller;

import de.dhbw.orchestrachive.orchestrarchive.dto.musicbrainz.MusicBrainzWork;
import de.dhbw.orchestrachive.orchestrarchive.service.MusicBrainzService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/music-search")
public class MusicBrainzController {

    private final MusicBrainzService service;

    public MusicBrainzController(MusicBrainzService service) {
        this.service = service;
    }

    @GetMapping
    public List<MusicBrainzWork> search(@RequestParam String title) {
        return service.searchWorks(title);
    }
}
