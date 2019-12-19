package com.kodilla.erenovation_service.controller;

import com.kodilla.erenovation_service.dto.PricingDto;
import com.kodilla.erenovation_service.exception.PricingNotFoundException;
import com.kodilla.erenovation_service.exception.UserNotFoundException;
import com.kodilla.erenovation_service.service.PricingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/pricing")
@CrossOrigin("*")
@Slf4j
public class PricingController {

    @Autowired
    private PricingService pricingService;

    @PostMapping()
    public PricingDto createPricingForUser(@RequestParam long userId) throws UserNotFoundException {
        log.info("Creating new pricing");
        return pricingService.createPricingFor(userId);
    }

    @GetMapping(value = "all")
    public List<PricingDto> getAllPricings() {
        log.info("Searching for all the pricings");
        return pricingService.getPricings();
    }

    @GetMapping
    public PricingDto getPricingById(@RequestParam long pricingId) throws PricingNotFoundException {
        log.info("Searching for pricing with ID {}", pricingId);
        return pricingService.getPricing(pricingId);
    }
}
