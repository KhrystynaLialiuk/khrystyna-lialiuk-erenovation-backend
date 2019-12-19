package com.kodilla.erenovation_service.mapper;

import com.kodilla.erenovation_service.domain.ServiceType;
import com.kodilla.erenovation_service.dto.ServiceTypeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceTypeMapper {

    @Autowired
    private ServiceMapper serviceMapper;

    public ServiceType toServiceType(final ServiceTypeDto serviceTypeDto) {
        ServiceType serviceType = new ServiceType();
        serviceType.setId(serviceTypeDto.getId());
        serviceType.setTitle(serviceTypeDto.getTitle());
        return serviceType;
    }

    public ServiceTypeDto toServiceTypeDto(final ServiceType serviceType) {
        ServiceTypeDto serviceTypeDto = new ServiceTypeDto();
        serviceTypeDto.setId(serviceType.getId());
        serviceTypeDto.setTitle(serviceType.getTitle());
        if (serviceType.getServices() != null) {
            serviceTypeDto.setServices(serviceMapper.toServiceDtoList(serviceType.getServices()));
        }
        return serviceTypeDto;
    }
}
