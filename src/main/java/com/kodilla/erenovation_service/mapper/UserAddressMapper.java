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

    public UserAddressDto toUserAddressDto(final UserAddress address) {
        return new UserAddressDto(
                address.getId(),
                address.getCity(),
                address.getStreet(),
                address.getBuilding(),
                address.getApartment(),
                address.getPostalCode()
        );
    }
}
