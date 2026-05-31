package de.dhbw.orchestrachive.orchestrarchive.dto.musicbrainz;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MusicBrainzWorkResponse(
        int count,
        List<MusicBrainzWork> works
) {
}
