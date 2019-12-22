package com.kodilla.erenovation_service.geo_client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeoLocationDto {

    @JsonProperty("lat")
    private String lat;

    @JsonProperty("lon")
    private String lon;
}
