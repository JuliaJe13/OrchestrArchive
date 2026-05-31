package de.dhbw.orchestrachive.orchestrarchive.dto.musicbrainz;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MusicBrainzArtist(
        String name
) {
}
