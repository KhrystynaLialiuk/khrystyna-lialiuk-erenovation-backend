package com.kodilla.erenovation_service.distance_client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Value;

import java.net.URI;

@Component
public class DistanceClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${geo.lat}")
    private String originLat;

    @Value("${geo.lon}")
    private String originLon;

    @Value("${rapidapi.api.url}")
    private String rapidApiUrl;

    @Value("${rapidapi.host}")
    private String rapidApiHost;

    @Value("${rapidapi.host.value}")
    private String rapidApiHostValue;

    @Value("${rapidapi.key}")
    private String rapidApiKey;

    @Value("${rapidapi.key.value}")
    private String rapidApiKeyValue;

    public ResponseEntity<DistanceDto> getDistance(String destinationLat, String destinationLon) {
        String url = rapidApiUrl;
        String origins = originLat + "," + originLon;
        String destinations = destinationLat + "," + destinationLon;
        URI uri = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("origins", origins)
                .queryParam("destinations", destinations)
                .build().encode().toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.set(rapidApiHost, rapidApiHostValue);
        headers.set(rapidApiKey, rapidApiKeyValue);

        HttpEntity request = new HttpEntity(headers);

        return restTemplate.exchange(uri, HttpMethod.GET, request, DistanceDto.class);
    }
}
