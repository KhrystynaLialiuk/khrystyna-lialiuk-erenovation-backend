package com.kodilla.erenovation_service.service;

import com.kodilla.erenovation_service.distance_client.DistanceClient;
import com.kodilla.erenovation_service.distance_client.DistanceDto;
import com.kodilla.erenovation_service.geo_client.GeoClient;
import com.kodilla.erenovation_service.geo_client.GeoLocationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransportCostService {

    @Value("${transport.cost}")
    private String costPerMeter;

    @Autowired
    private GeoClient geoClient;

    @Autowired
    private DistanceClient distanceClient;

    public BigDecimal calculateTransportCost(String city, String street, String postalCode) {
        List<GeoLocationDto> geoLocationDtoList = geoClient.getLocation(city, street, postalCode);
        if (geoLocationDtoList.size() == 0) {
            return new BigDecimal("0");
        }
        GeoLocationDto destinationGeoData = geoLocationDtoList.get(0);

        ResponseEntity<DistanceDto> distanceDtoResponseEntity = distanceClient.getDistance(
                destinationGeoData.getLat(), destinationGeoData.getLon());
        if (distanceDtoResponseEntity.getBody() == null) {
            return new BigDecimal("0");
        }

        String distance = distanceDtoResponseEntity.getBody().getDistances()[0][0];
        return new BigDecimal(distance).multiply(new BigDecimal(costPerMeter));
    }
}
