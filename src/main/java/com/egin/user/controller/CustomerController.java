package com.egin.user.controller;

import com.egin.common.model.dto.response.CustomPagingResponse;
import com.egin.common.model.dto.response.CustomResponse;
import com.egin.user.model.Customer;
import com.egin.user.model.dto.request.CustomerCreateRequest;
import com.egin.user.model.dto.request.CustomerPagingRequest;
import com.egin.user.model.dto.request.CustomerUpdateRequest;
import com.egin.user.model.mapper.CustomerCustomPageToCustomPagingResponseMapper;
import com.egin.user.service.customer.CustomerService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(
            final CustomerService customerService
    ) {
        this.customerService = customerService;
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN')")
    public CustomResponse<Customer> createCustomer(
            @RequestBody @Valid final CustomerCreateRequest request
    ) {
        final Customer customer = customerService.createCustomer(request);
        return CustomResponse.successOf(customer);
    }

    @PutMapping("/{customerId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN')")
    public CustomResponse<Customer> updateCustomer(
            @PathVariable final String customerId,
            @RequestBody @Valid final CustomerUpdateRequest request
    ) {
        final Customer customer = customerService.updateCustomer(customerId, request);
        return CustomResponse.successOf(customer);
    }

    @DeleteMapping("/{customerId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN')")
    public CustomResponse<Void> deleteCustomer(
            @PathVariable final String customerId
    ) {
        customerService.deleteCustomer(customerId);
        return CustomResponse.SUCCESS;
    }

    @GetMapping("/{customerId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN')")
    public CustomResponse<Customer> getCustomerById(
            @PathVariable final String customerId
    ) {
        final Customer customer = customerService.getCustomerById(customerId);
        return CustomResponse.successOf(customer);
    }

    @GetMapping()
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN')")
    public CustomResponse<CustomPagingResponse<Customer>> getAllCustomers(
            @RequestBody @Valid final CustomerPagingRequest pagingRequest
    ) {
        final CustomPagingResponse<Customer> customers = CustomerCustomPageToCustomPagingResponseMapper
                .toCustomPagingResponse(
                        customerService.getAllCustomers(pagingRequest)
                );
        return CustomResponse.successOf(customers);
    }

}

