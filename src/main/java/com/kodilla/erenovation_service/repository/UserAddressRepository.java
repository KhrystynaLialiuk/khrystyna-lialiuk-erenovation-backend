package com.kodilla.erenovation_service.repository;

import com.kodilla.erenovation_service.domain.UserAddress;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAddressRepository extends CrudRepository<UserAddress, Long> {
    @Override
    List<UserAddress> findAll();
}
