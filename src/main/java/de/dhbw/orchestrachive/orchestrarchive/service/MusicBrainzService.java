package de.dhbw.orchestrachive.orchestrarchive.service;

import de.dhbw.orchestrachive.orchestrarchive.dto.musicbrainz.MusicBrainzWork;
import de.dhbw.orchestrachive.orchestrarchive.dto.musicbrainz.MusicBrainzWorkResponse;
import de.dhbw.orchestrachive.orchestrarchive.exception.ExternalApiException;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.util.List;
import java.util.Collections;

@Service
public class MusicBrainzService {

    private final RestClient musicBrainzRestClient;

    public MusicBrainzService(RestClient musicBrainzRestClient) {
        this.musicBrainzRestClient = musicBrainzRestClient;
    }

    public List<MusicBrainzWork> searchWorks(String title) {
        try {
            MusicBrainzWorkResponse response = musicBrainzRestClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/ws/2/work")
                            .queryParam("query", title)
                            .queryParam("fmt", "json")
                            .build())
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                        throw new ExternalApiException("MusicBrainz API client error: " + res.getStatusCode());
                    })
                    .onStatus(HttpStatusCode::is5xxServerError, (req, res) -> {
                        throw new ExternalApiException("MusicBrainz API server error: " + res.getStatusCode());
                    })
                    .body(MusicBrainzWorkResponse.class);

            if (response == null || response.works() == null) {
                return Collections.emptyList();
            }
            return response.works();
        } catch (RestClientException ex) {
            throw new ExternalApiException("Failed to call MusicBraniz: " + ex.getMessage(), ex);
        }
    }
}
