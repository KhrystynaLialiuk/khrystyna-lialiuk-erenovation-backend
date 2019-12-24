package com.kodilla.erenovation_service.service;

import com.kodilla.erenovation_service.dto.PricingRecordDto;
import com.kodilla.erenovation_service.exception.PricingNotFoundException;
import com.kodilla.erenovation_service.exception.PricingRecordNotFoundException;
import com.kodilla.erenovation_service.exception.ServiceNotFoundException;
import com.kodilla.erenovation_service.exception.UserNotFoundException;
import com.kodilla.erenovation_service.mapper.PricingRecordMapper;
import com.kodilla.erenovation_service.repository.PricingRecordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PricingRecordService {

    @Autowired
    private PricingRecordRepository pricingRecordRepository;

    @Autowired
    private PricingRecordMapper pricingRecordMapper;

    @Autowired
    private CalculationService calculationService;

    @Autowired
    private PricingService pricingService;

    public HttpStatus createPricingRecord(final PricingRecordDto pricingRecordDto)
            throws PricingNotFoundException, ServiceNotFoundException, UserNotFoundException {
        PricingRecordDto calculatedPricingRecordDto = calculationService
                .calculatePricingRecordPrice(pricingRecordDto);
        if (calculatedPricingRecordDto != null) {
            pricingRecordRepository.save(pricingRecordMapper.toPricingRecord(calculatedPricingRecordDto));
            pricingService.updatePricing(pricingService.getPricing(pricingRecordDto.getPricingId()));
            return HttpStatus.CREATED;
        }
        return HttpStatus.BAD_REQUEST;
    }

    public PricingRecordDto updatePricingRecord(final PricingRecordDto pricingRecordDto)
            throws PricingNotFoundException, ServiceNotFoundException, UserNotFoundException {
        PricingRecordDto calculatedPricingRecordDto = calculationService.
                calculatePricingRecordPrice(pricingRecordDto);
        if (calculatedPricingRecordDto != null) {
            PricingRecordDto updatedPricingRecordDto = pricingRecordMapper.toPricingRecordDto(pricingRecordRepository
                    .save(pricingRecordMapper.toPricingRecord(calculatedPricingRecordDto)));
            pricingService.updatePricing(pricingService.getPricing(pricingRecordDto.getPricingId()));
            return updatedPricingRecordDto;
        }
        return pricingRecordDto;
    }

    public void deletePricingRecord(final long pricingRecordId, final long pricingId)
            throws PricingNotFoundException, UserNotFoundException, PricingRecordNotFoundException {
        try {
            pricingRecordRepository.deleteById(pricingRecordId);
        } catch (IllegalArgumentException e) {
            throw new PricingRecordNotFoundException();
        }
        pricingService.updatePricing(pricingService.getPricing(pricingId));
    }
}
