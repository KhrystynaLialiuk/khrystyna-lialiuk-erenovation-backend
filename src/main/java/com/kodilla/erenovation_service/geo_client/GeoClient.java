package com.kodilla.erenovation_service.geo_client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class GeoClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${location.api}")
    private String locationApiUrl;

    public List<GeoLocationDto> getLocation(String city, String street, String postalcode) {
        String url = locationApiUrl;
        URI uri = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("city", city)
                .queryParam("street", street)
                .queryParam("postalcode", postalcode)
                .queryParam("format", "json")
                .queryParam("limit", 1)
                .build().encode().toUri();
        try {
            GeoLocationDto[] response = restTemplate.getForObject(uri, GeoLocationDto[].class);
            return Arrays.asList(Optional.ofNullable(response).orElse(new GeoLocationDto[0]));
        } catch (RestClientException e) {
            return new ArrayList<>();
        }
    }
}
