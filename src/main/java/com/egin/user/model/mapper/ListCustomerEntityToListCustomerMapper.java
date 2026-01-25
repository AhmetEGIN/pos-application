package com.egin.user.model.mapper;

import com.egin.user.model.Customer;
import com.egin.user.model.entity.CustomerEntity;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class ListCustomerEntityToListCustomerMapper {

    public List<Customer> toCustomerList(List<CustomerEntity> customerEntities) {
        return customerEntities.stream()
                .map(CustomerEntityToCustomerMapper::toCustomer)
                .collect(Collectors.toList());
    }

}

