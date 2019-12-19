package com.kodilla.erenovation_service.mapper;

import com.kodilla.erenovation_service.domain.UserAddress;
import com.kodilla.erenovation_service.dto.UserAddressDto;
import org.springframework.stereotype.Component;

@Component
public class UserAddressMapper {
    public UserAddress toUserAddress(final UserAddressDto userAddressDto) {
        UserAddress userAddress = new UserAddress();
        userAddress.setId(userAddressDto.getId());
        userAddress.setCity(userAddressDto.getCity());
        userAddress.setStreet(userAddressDto.getStreet());
        userAddress.setBuilding(userAddressDto.getBuilding());
        userAddress.setApartment(userAddressDto.getApartment());
        userAddress.setPostalCode(userAddressDto.getPostalCode());
        return userAddress;
    }

    public UserAddressDto toUserAddressDto(final UserAddress userAddress) {
        UserAddressDto userAddressDto = new UserAddressDto();
        userAddressDto.setId(userAddress.getId());
        userAddressDto.setCity(userAddress.getCity());
        userAddressDto.setStreet(userAddress.getStreet());
        userAddressDto.setBuilding(userAddress.getBuilding());
        userAddressDto.setApartment(userAddress.getApartment());
        userAddressDto.setPostalCode(userAddress.getPostalCode());
        return userAddressDto;
    }
}
