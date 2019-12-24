package com.kodilla.erenovation_service.data;

import com.kodilla.erenovation_service.domain.Service;
import com.kodilla.erenovation_service.domain.ServiceType;
import com.kodilla.erenovation_service.repository.ServiceRepository;
import com.kodilla.erenovation_service.repository.ServiceTypeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ServiceDataCreatorTestSuite {

    @InjectMocks
    private ServiceDataCreator serviceDataCreator;

    @Mock
    private ServiceRepository serviceRepository;

    @Mock
    private ServiceTypeRepository serviceTypeRepository;

    @Test
    public void testCreateServiceData() {
        //Given

        //When
        serviceDataCreator.createServiceData();

        //Then
        verify(serviceTypeRepository, times(2)).save(any(ServiceType.class));
        verify(serviceRepository, times(3)).save(any(Service.class));
    }
}
