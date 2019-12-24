package com.kodilla.erenovation_service.controller;

import com.kodilla.erenovation_service.dto.PricingRecordDto;
import com.kodilla.erenovation_service.exception.PricingNotFoundException;
import com.kodilla.erenovation_service.exception.PricingRecordNotFoundException;
import com.kodilla.erenovation_service.exception.ServiceNotFoundException;
import com.kodilla.erenovation_service.exception.UserNotFoundException;
import com.kodilla.erenovation_service.service.PricingRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/pricingposition")
@CrossOrigin("*")
@Slf4j
public class PricingRecordController {

    @Autowired
    private PricingRecordService pricingRecordService;

    @PostMapping
    public ResponseEntity<String> createPricingRecord(@RequestBody PricingRecordDto pricingRecordDto)
            throws PricingNotFoundException, ServiceNotFoundException, UserNotFoundException {
        log.info("Creating new record for pricing {}", pricingRecordDto.getPricingId());
        return new ResponseEntity<>(pricingRecordService.createPricingRecord(pricingRecordDto));
    }

    /*@GetMapping
    public PricingRecordDto getRecordById(@RequestParam long pricingRecordId) throws PricingRecordNotFoundException {
        log.info("Searching for pricing record with ID {}", pricingRecordId);
        return pricingRecordService.getPricingRecord(pricingRecordId);
    }

    @GetMapping(value = "all")
    public List<PricingRecordDto> getAllPricingRecords() {
        log.info("Searching for all the pricing records");
        return pricingRecordService.getPricingRecords();
    }*/

    @PutMapping
    public PricingRecordDto updatePricingRecord(@RequestBody PricingRecordDto pricingRecordDto)
            throws PricingNotFoundException, ServiceNotFoundException, UserNotFoundException {
        log.info("Updating pricing record with ID {}", pricingRecordDto.getId());
        return pricingRecordService.updatePricingRecord(pricingRecordDto);
    }

    @DeleteMapping
    public void deletePricingRecord(@RequestParam("pricingRecordId") long pricingRecordId,
                                    @RequestParam("pricingId") long pricingId)
            throws UserNotFoundException, PricingNotFoundException, PricingRecordNotFoundException {
        log.info("Deleting pricing record with ID {}", pricingRecordId);
        pricingRecordService.deletePricingRecord(pricingRecordId, pricingId);
    }
}
