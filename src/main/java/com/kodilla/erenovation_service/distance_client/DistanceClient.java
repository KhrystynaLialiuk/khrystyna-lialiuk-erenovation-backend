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

    public ResponseEntity<DistanceDto> getDistance(String destinationLat, String destinationLon) {
        String url = "https://trueway-matrix.p.rapidapi.com/CalculateDrivingMatrix";
        String origins = originLat + "," + originLon;
        String destinations = destinationLat + "," + destinationLon;
        URI uri = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("origins", origins)
                .queryParam("destinations", destinations)
                .build().encode().toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-rapidapi-host", "trueway-matrix.p.rapidapi.com");
        headers.set("x-rapidapi-key", "76954268aamsh6f08d5028e7f0afp1554b2jsn0d619f00b933");

        HttpEntity request = new HttpEntity(headers);

        return restTemplate.exchange(uri, HttpMethod.GET, request, DistanceDto.class);
    }
}
