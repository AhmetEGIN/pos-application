package com.egin.user.model.mapper;

import com.egin.user.model.Customer;
import com.egin.user.model.entity.CustomerEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerEntityToCustomerMapper {

    public Customer toCustomer(final CustomerEntity customerEntity) {
        return Customer.builder()
                .id(customerEntity.getId())
                .firstName(customerEntity.getFirstName())
                .lastName(customerEntity.getLastName())
                .phoneNumber(customerEntity.getPhoneNumber())
                .address(customerEntity.getAddress())
                .city(customerEntity.getCity())
                .country(customerEntity.getCountry())
                .postalCode(customerEntity.getPostalCode())
                .createdAt(customerEntity.getCreatedAt())
                .updatedAt(customerEntity.getUpdatedAt())
                .createdBy(customerEntity.getCreatedBy())
                .updatedBy(customerEntity.getUpdatedBy())
                .build();
    }

}

