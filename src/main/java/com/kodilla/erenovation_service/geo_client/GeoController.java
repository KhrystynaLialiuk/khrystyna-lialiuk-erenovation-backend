package com.kodilla.erenovation_service.geo_client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/geo")
@CrossOrigin("*")
@Slf4j
public class GeoController {

    @Autowired
    private GeoClient geoClient;

    @GetMapping
    public void getGeo(@RequestParam String city, @RequestParam String street, @RequestParam String postalcode) {
        List<GeoLocationDto> list = geoClient.getLocation(city, street, postalcode);
        log.info("List size should by 1, is {}", list.size());
        try {
            log.info("Destination latitude {}", list.get(0).getLat());
            log.info("Destination longitude {}", list.get(0).getLon());
        } catch (NullPointerException e) {
            log.warn("Destination geo data not found");
        }
    }
}
