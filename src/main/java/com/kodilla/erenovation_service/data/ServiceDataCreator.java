package com.kodilla.erenovation_service.data;

import com.kodilla.erenovation_service.domain.Service;
import com.kodilla.erenovation_service.domain.ServiceType;
import com.kodilla.erenovation_service.repository.ServiceRepository;
import com.kodilla.erenovation_service.repository.ServiceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ServiceDataCreator {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ServiceTypeRepository serviceTypeRepository;

    public void createServiceData() {
        ServiceType serviceType1 = new ServiceType();
        serviceType1.setTitle("Painting");
        serviceTypeRepository.save(serviceType1);

        ServiceType serviceType2 = new ServiceType();
        serviceType2.setTitle("Hydraulics");
        serviceTypeRepository.save(serviceType2);

        Service service1 = new Service();
        service1.setTitle("Wall preparation");
        service1.setRegularUnitPrice(new BigDecimal(5));
        service1.setDiscountUnitPrice(new BigDecimal(4));
        service1.setThresholdValue(100);
        service1.setServiceType(serviceType1);
        serviceRepository.save(service1);

        Service service2 = new Service();
        service2.setTitle("Painting, 1 layer");
        service2.setRegularUnitPrice(new BigDecimal(10));
        service2.setDiscountUnitPrice(new BigDecimal(9));
        service2.setThresholdValue(100);
        service2.setServiceType(serviceType1);
        serviceRepository.save(service2);

        Service service3 = new Service();
        service3.setTitle("Lavatory pan installation");
        service3.setRegularUnitPrice(new BigDecimal(300));
        service3.setDiscountUnitPrice(new BigDecimal(290));
        service3.setThresholdValue(5);
        service3.setServiceType(serviceType2);
        serviceRepository.save(service3);
    }
}
