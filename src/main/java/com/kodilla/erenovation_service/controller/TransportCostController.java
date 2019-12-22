package com.kodilla.erenovation_service.controller;

import com.kodilla.erenovation_service.service.TransportCostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/v1/transport")
@CrossOrigin("*")
@Slf4j
public class TransportCostController {

    @Autowired
    private TransportCostService transportCostService;

    @GetMapping
    public BigDecimal getCost(@RequestParam("city") String city,
                              @RequestParam("street") String street,
                              @RequestParam("postalcode") String postalcode) {
        log.info("Calculating transportation cost");
        return transportCostService.calculateTransportCost(city, street, postalcode);
    }
}
