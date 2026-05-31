package de.dhbw.orchestrachive.orchestrarchive.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import java.time.Duration;

@Configuration
public class MusicBrainzConfig {

    @Value("${musicbrainz.api.base-url}")
    private String baseUrl;

    @Value("${musicbrainz.api.connect-timeout-ms}")
    private int connectionTimeoutsMs;

    @Value("${musicbrainz.api.read-timeout-ms}")
    private int readTimeoutMs;

    @Bean
    public RestClient musicBrainzRestClient() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(Duration.ofMillis((connectionTimeoutsMs)));
        factory.setReadTimeout(Duration.ofMillis(readTimeoutMs));

        return RestClient.builder()
                .baseUrl(baseUrl)
                .requestFactory(factory)
                .defaultHeader("Accept", "application/json")
                .build();
    }
}
