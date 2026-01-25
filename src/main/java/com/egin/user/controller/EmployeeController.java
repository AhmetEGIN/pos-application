package com.egin.user.controller;

import com.egin.common.model.dto.response.CustomPagingResponse;
import com.egin.common.model.dto.response.CustomResponse;
import com.egin.user.model.Employee;
import com.egin.user.model.dto.request.EmployeeByBranchPagingRequest;
import com.egin.user.model.dto.request.EmployeeByStorePagingRequest;
import com.egin.user.model.dto.request.EmployeeCreateRequest;
import com.egin.user.model.dto.request.EmployeeUpdateRequest;
import com.egin.user.model.mapper.EmployeeCustomPageToCustomPagingResponseMapper;
import com.egin.user.service.employee.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(
            final EmployeeService employeeService
    ) {
        this.employeeService = employeeService;
    }

    @PostMapping("/store")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN')")
    public CustomResponse<Employee> createStoreEmployee(
            @RequestBody @Valid final EmployeeCreateRequest request
    ) {
        final Employee employee = employeeService.createStoreEmployee(request);
        return CustomResponse.successOf(employee);
    }

    @PostMapping("/branch")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN', 'BRANCH_MANAGER')")
    public CustomResponse<Employee> createBranchEmployee(
            @RequestBody @Valid final EmployeeCreateRequest request
    ) {
        final Employee employee = employeeService.createBranchEmployee(request);
        return CustomResponse.successOf(employee);
    }

    @PutMapping("/{employeeId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN', 'BRANCH_MANAGER')")
    public CustomResponse<Employee> updateEmployee(
            @PathVariable final String employeeId,
            @RequestBody @Valid final EmployeeUpdateRequest request
    ) {
        final Employee employee = employeeService.updateEmployee(employeeId, request);
        return CustomResponse.successOf(employee);
    }

    @DeleteMapping("/{employeeId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN')")
    public CustomResponse<Void> deleteEmployee(
            @PathVariable final String employeeId
    ) {
        employeeService.deleteEmployee(employeeId);
        return CustomResponse.SUCCESS;
    }

    @PostMapping("/store/{storeId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN')")
    public CustomResponse<CustomPagingResponse<Employee>> getStoreEmployees(
            @PathVariable final String storeId,
            @RequestBody @Valid final EmployeeByStorePagingRequest pagingRequest
    ) {
        final CustomPagingResponse<Employee> employees = EmployeeCustomPageToCustomPagingResponseMapper
                .toCustomPagingResponse(
                        employeeService.findStoreEmployees(storeId, pagingRequest)
                );
        return CustomResponse.successOf(employees);
    }

    @PostMapping("/branch/{branchId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN', 'BRANCH_MANAGER')")
    public CustomResponse<CustomPagingResponse<Employee>> getBranchEmployees(
            @PathVariable final String branchId,
            @RequestBody @Valid final EmployeeByBranchPagingRequest pagingRequest
    ) {
        final CustomPagingResponse<Employee> employees = EmployeeCustomPageToCustomPagingResponseMapper
                .toCustomPagingResponse(
                        employeeService.findBranchEmployees(branchId, pagingRequest)
                );
        return CustomResponse.successOf(employees);
    }

    @GetMapping("/{employeeId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN', 'BRANCH_MANAGER')")
    public CustomResponse<Employee> getEmployeeById(
            @PathVariable final String employeeId
    ) {
        final Employee employee = employeeService.getEmployeeById(employeeId);
        return CustomResponse.successOf(employee);
    }
}

