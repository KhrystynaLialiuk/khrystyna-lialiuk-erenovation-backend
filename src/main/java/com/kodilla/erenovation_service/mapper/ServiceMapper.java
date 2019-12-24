package com.kodilla.erenovation_service.mapper;

import com.kodilla.erenovation_service.domain.Service;
import com.kodilla.erenovation_service.dto.ServiceDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ServiceMapper {

    public ServiceDto toServiceDto(final Service service) {
        ServiceDto serviceDto = new ServiceDto();
        serviceDto.setId(service.getId());
        serviceDto.setTitle(service.getTitle());
        serviceDto.setRegularUnitPrice(service.getRegularUnitPrice());
        serviceDto.setDiscountUnitPrice(service.getDiscountUnitPrice());
        serviceDto.setThresholdValue(service.getThresholdValue());
        serviceDto.setServiceTypeId(service.getServiceType().getId());
        return serviceDto;
    }

    public List<ServiceDto> toServiceDtoList(final List<Service> serviceList) {
        return serviceList.stream()
                .map(this::toServiceDto)
                .collect(Collectors.toList());
    }
}
