package com.kodilla.erenovation_service.mapper;

import com.kodilla.erenovation_service.domain.PricingRecord;
import com.kodilla.erenovation_service.dto.PricingRecordDto;
import com.kodilla.erenovation_service.exception.PricingNotFoundException;
import com.kodilla.erenovation_service.exception.ServiceNotFoundException;
import com.kodilla.erenovation_service.repository.PricingRepository;
import com.kodilla.erenovation_service.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PricingRecordMapper {

    @Autowired
    private PricingRepository pricingRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    public PricingRecord toPricingRecord(final PricingRecordDto pricingRecordDto) throws PricingNotFoundException,
            ServiceNotFoundException {
        PricingRecord pricingRecord = new PricingRecord();
        pricingRecord.setId(pricingRecordDto.getId());
        pricingRecord.setPrice(pricingRecordDto.getPrice());
        pricingRecord.setPriceType(pricingRecordDto.getPriceType());
        pricingRecord.setQuantityOrMeters(pricingRecordDto.getQuantityOrMeters());
        pricingRecord.setPricing(pricingRepository.findById(pricingRecordDto.getPricingId())
                .orElseThrow(PricingNotFoundException::new));
        pricingRecord.setService(serviceRepository.findById(pricingRecordDto.getServiceId())
                .orElseThrow(ServiceNotFoundException::new));
        return pricingRecord;
    }

    public PricingRecordDto toPricingRecordDto(final PricingRecord pricingRecord) {
        PricingRecordDto pricingRecordDto = new PricingRecordDto();
        pricingRecordDto.setId(pricingRecord.getId());
        pricingRecordDto.setQuantityOrMeters(pricingRecord.getQuantityOrMeters());
        pricingRecordDto.setPrice(pricingRecord.getPrice());
        pricingRecordDto.setPriceType(pricingRecord.getPriceType());
        if (pricingRecord.getPricing() != null) {
            pricingRecordDto.setPricingId(pricingRecord.getPricing().getId());
        }
        if (pricingRecord.getService() != null) {
            pricingRecordDto.setServiceId(pricingRecord.getService().getId());
            pricingRecordDto.setServiceTitle(pricingRecord.getService().getTitle());
        }
        return pricingRecordDto;
    }

    public List<PricingRecordDto> toPricingRecordDtoList(final List<PricingRecord> pricingRecordList) {
        return pricingRecordList.stream()
                .map(this::toPricingRecordDto)
                .collect(Collectors.toList());
    }
}
