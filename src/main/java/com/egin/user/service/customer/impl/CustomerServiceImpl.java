package com.egin.user.service.customer.impl;

import com.egin.common.model.CustomPage;
import com.egin.user.exception.CustomerNotFoundException;
import com.egin.user.model.Customer;
import com.egin.user.model.dto.request.CustomerCreateRequest;
import com.egin.user.model.dto.request.CustomerPagingRequest;
import com.egin.user.model.dto.request.CustomerUpdateRequest;
import com.egin.user.model.entity.CustomerEntity;
import com.egin.user.model.mapper.CustomerCreateRequestToCustomerEntityMapper;
import com.egin.user.model.mapper.CustomerEntityToCustomerMapper;
import com.egin.user.model.mapper.CustomerUpdateRequestToCustomerEntityMapper;
import com.egin.user.model.mapper.ListCustomerEntityToListCustomerMapper;
import com.egin.user.repository.CustomerRepository;
import com.egin.user.service.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(final CustomerCreateRequest request) {
        final CustomerEntity customerEntity = CustomerCreateRequestToCustomerEntityMapper
                .toCustomerEntity(request);

        final CustomerEntity savedCustomerEntity = customerRepository.save(customerEntity);

        return CustomerEntityToCustomerMapper.toCustomer(savedCustomerEntity);
    }

    @Override
    public Customer updateCustomer(final String customerId, final CustomerUpdateRequest request) {
        final CustomerEntity customerEntity = customerRepository.findById(customerId)
                .orElseThrow(CustomerNotFoundException::new);

        CustomerUpdateRequestToCustomerEntityMapper.toCustomerEntity(request, customerEntity);

        final CustomerEntity updatedCustomerEntity = customerRepository.save(customerEntity);

        return CustomerEntityToCustomerMapper.toCustomer(updatedCustomerEntity);
    }

    @Override
    public void deleteCustomer(final String customerId) {
        final CustomerEntity customerEntity = customerRepository.findById(customerId)
                .orElseThrow(CustomerNotFoundException::new);

        customerRepository.delete(customerEntity);
    }

    @Override
    public Customer getCustomerById(final String customerId) {
        final CustomerEntity customerEntity = customerRepository.findById(customerId)
                .orElseThrow(CustomerNotFoundException::new);

        return CustomerEntityToCustomerMapper.toCustomer(customerEntity);
    }

    @Override
    public CustomPage<Customer> getAllCustomers(final CustomerPagingRequest pagingRequest) {
        final Page<CustomerEntity> customerEntityPage = customerRepository.findAll(pagingRequest.toPageable());

        final List<Customer> customers = ListCustomerEntityToListCustomerMapper
                .toCustomerList(customerEntityPage.getContent());

        return CustomPage.of(customers, customerEntityPage);
    }

}

