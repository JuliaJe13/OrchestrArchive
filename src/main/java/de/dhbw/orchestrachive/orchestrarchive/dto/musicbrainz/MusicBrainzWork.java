package de.dhbw.orchestrachive.orchestrarchive.dto.musicbrainz;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MusicBrainzWork(
    String id,
    String title,
    List<MusicBrainzRelation> relations
) {
}
