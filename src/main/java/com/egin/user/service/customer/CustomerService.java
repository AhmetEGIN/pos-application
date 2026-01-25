package com.egin.user.service.customer;

import com.egin.common.model.CustomPage;
import com.egin.user.model.Customer;
import com.egin.user.model.dto.request.CustomerCreateRequest;
import com.egin.user.model.dto.request.CustomerPagingRequest;
import com.egin.user.model.dto.request.CustomerUpdateRequest;

public interface CustomerService {

    Customer createCustomer(final CustomerCreateRequest request);

    Customer updateCustomer(final String customerId, final CustomerUpdateRequest request);

    void deleteCustomer(final String customerId);

    Customer getCustomerById(final String customerId);

    CustomPage<Customer> getAllCustomers(final CustomerPagingRequest pagingRequest);

    CustomPage<Customer> searchCustomersByKeyword(final String keyword, final CustomerPagingRequest pagingRequest);
}

