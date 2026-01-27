package com.egin.user.model.mapper;

import com.egin.user.model.Customer;
import com.egin.user.model.entity.CustomerEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerToCustomerEntityMapper {


    public CustomerEntity toUserEntity(Customer customer) {

            return CustomerEntity.builder()
                    .id(customer.getId())
                    .firstName(customer.getFirstName())
                    .lastName(customer.getLastName())
                    .phoneNumber(customer.getPhoneNumber())
                    .address(customer.getAddress())
                    .city(customer.getCity())
                    .country(customer.getCountry())
                    .postalCode(customer.getPostalCode())
                    .build();
    }


}
