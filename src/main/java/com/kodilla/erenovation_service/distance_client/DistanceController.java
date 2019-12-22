package com.kodilla.erenovation_service.distance_client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/distance")
@CrossOrigin("*")
@Slf4j
public class DistanceController {

    @Autowired
    private DistanceClient distanceClient;

    @GetMapping
    public void getDistance(@RequestParam("lat") String lat, @RequestParam("lon") String lon) {
        ResponseEntity<DistanceDto> distance = distanceClient.getDistance(lat, lon);
        try {
            String[][] result = distance.getBody().getDistances();
            log.info("Length of distance object should be 1, is {}", result.length);
            log.info("Distance in meters: {}", result[0][0]);
        } catch (NullPointerException e) {
            log.warn("Distance object is null");
        }
    }
}
