package com.egin.user.model.mapper;

import com.egin.user.model.Customer;
import com.egin.user.model.dto.request.CustomerCreateRequest;
import com.egin.user.model.entity.CustomerEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerCreateRequestToCustomerEntityMapper {

    public CustomerEntity toCustomerEntity(CustomerCreateRequest request) {
        return CustomerEntity.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .address(request.getAddress())
                .city(request.getCity())
                .country(request.getCountry())
                .postalCode(request.getPostalCode())
                .build();
    }
}

