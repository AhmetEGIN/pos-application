package com.egin.user.model.mapper;

import com.egin.user.model.dto.request.CustomerUpdateRequest;
import com.egin.user.model.entity.CustomerEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerUpdateRequestToCustomerEntityMapper {

    public void toCustomerEntity(
            CustomerUpdateRequest request,
            CustomerEntity customerEntity
    ) {
        if (request.getFirstName() != null) {
            customerEntity.setFirstName(request.getFirstName());
        }
        if (request.getLastName() != null) {
            customerEntity.setLastName(request.getLastName());
        }
        if (request.getPhoneNumber() != null) {
            customerEntity.setPhoneNumber(request.getPhoneNumber());
        }
        if (request.getAddress() != null) {
            customerEntity.setAddress(request.getAddress());
        }
        if (request.getCity() != null) {
            customerEntity.setCity(request.getCity());
        }
        if (request.getCountry() != null) {
            customerEntity.setCountry(request.getCountry());
        }
        if (request.getPostalCode() != null) {
            customerEntity.setPostalCode(request.getPostalCode());
        }
    }
}

