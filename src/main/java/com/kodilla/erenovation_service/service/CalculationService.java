package com.kodilla.erenovation_service.service;

import com.kodilla.erenovation_service.dto.PricingDto;
import com.kodilla.erenovation_service.dto.PricingRecordDto;
import com.kodilla.erenovation_service.factory.PatternFactory;
import com.kodilla.erenovation_service.factory.PricingPattern;
import com.kodilla.erenovation_service.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CalculationService {

    @Autowired
    private PatternFactory patternFactory;

    @Autowired
    private ServiceRepository serviceRepository;

    private PricingPattern pricingPattern;

    public PricingRecordDto calculatePricingRecordPrice(final PricingRecordDto pricingRecordDto) {
        Optional<com.kodilla.erenovation_service.domain.Service> service = serviceRepository
                .findByTitle(pricingRecordDto.getServiceTitle());
        if (service.isPresent()) {
            createPricingPatter(service.get(), pricingRecordDto.getQuantityOrMeters());
            BigDecimal price = pricingPattern.getPrice(service.get(), pricingRecordDto.getQuantityOrMeters());
            return setUpPricingRecordDto(service.get(), price, pricingPattern.getTitle(),
                    pricingRecordDto);
        }
        return null;
    }

    public PricingDto calculatePricingDto(final PricingDto pricingDto) {
        BigDecimal totalPrice = new BigDecimal(0);
        for (PricingRecordDto pricingRecordDto : pricingDto.getPricingRecordDtos()) {
            totalPrice = totalPrice.add(pricingRecordDto.getPrice());
        }
        return setUpPricingDto(totalPrice, pricingDto);
    }

    private PricingDto setUpPricingDto(final BigDecimal price, final PricingDto pricingDto) {
        PricingDto calculatedPricingDto = new PricingDto();
        calculatedPricingDto.setId(pricingDto.getId());
        calculatedPricingDto.setPrice(price);
        calculatedPricingDto.setDate(pricingDto.getDate());
        calculatedPricingDto.setUserId(pricingDto.getUserId());
        calculatedPricingDto.setReservationDtos(pricingDto.getReservationDtos());
        calculatedPricingDto.setPricingRecordDtos(pricingDto.getPricingRecordDtos());
        return calculatedPricingDto;
    }

    private void createPricingPatter(com.kodilla.erenovation_service.domain.Service service, int quantity) {
        if (quantity > service.getThresholdValue()) {
            pricingPattern = patternFactory.createPricingPattern(PatternFactory.DISCOUNT);
        } else {
            pricingPattern = patternFactory.createPricingPattern(PatternFactory.STANDARD);
        }
    }

    private PricingRecordDto setUpPricingRecordDto(com.kodilla.erenovation_service.domain.Service service,
                                                   BigDecimal price, String priceType, PricingRecordDto pricingRecordDto) {
        PricingRecordDto calculatedPricingRecordDto = new PricingRecordDto();
        calculatedPricingRecordDto.setId(pricingRecordDto.getId());
        calculatedPricingRecordDto.setPrice(price);
        calculatedPricingRecordDto.setPriceType(priceType);
        calculatedPricingRecordDto.setPricingId(pricingRecordDto.getPricingId());
        calculatedPricingRecordDto.setQuantityOrMeters(pricingRecordDto.getQuantityOrMeters());
        calculatedPricingRecordDto.setServiceId(service.getId());
        calculatedPricingRecordDto.setServiceTitle(service.getTitle());
        return calculatedPricingRecordDto;
    }
}
