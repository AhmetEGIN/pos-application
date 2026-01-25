package com.egin.user.service.employee;

import com.egin.common.model.CustomPage;
import com.egin.user.model.Employee;
import com.egin.user.model.dto.request.EmployeeByBranchPagingRequest;
import com.egin.user.model.dto.request.EmployeeByStorePagingRequest;
import com.egin.user.model.dto.request.EmployeeCreateRequest;
import com.egin.user.model.dto.request.EmployeeUpdateRequest;

public interface EmployeeService {

    Employee createStoreEmployee(final EmployeeCreateRequest request);

    Employee createBranchEmployee(final EmployeeCreateRequest request);

    Employee updateEmployee(final String employeeId, final EmployeeUpdateRequest request);

    void deleteEmployee(final String employeeId);

    CustomPage<Employee> findStoreEmployees(final String storeId, final EmployeeByStorePagingRequest pagingRequest);

    CustomPage<Employee> findBranchEmployees(final String branchId, final EmployeeByBranchPagingRequest pagingRequest);

    Employee getEmployeeById(final String employeeId);
}
