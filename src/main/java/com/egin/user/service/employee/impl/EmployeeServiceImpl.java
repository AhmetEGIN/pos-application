package com.egin.user.service.employee.impl;

import com.egin.branch.model.Branch;
import com.egin.branch.model.mapper.BranchToBranchEntityMapper;
import com.egin.branch.service.BranchService;
import com.egin.common.model.CustomPage;
import com.egin.store.model.Store;
import com.egin.store.model.mapper.StoreToStoreEntityMapper;
import com.egin.store.service.StoreService;
import com.egin.user.exception.EmployeeAlreadyExistsException;
import com.egin.user.exception.EmployeeNotFoundException;
import com.egin.user.model.Employee;
import com.egin.user.model.dto.request.EmployeeByBranchPagingRequest;
import com.egin.user.model.dto.request.EmployeeByStorePagingRequest;
import com.egin.user.model.dto.request.EmployeeCreateRequest;
import com.egin.user.model.dto.request.EmployeeUpdateRequest;
import com.egin.user.model.entity.EmployeeEntity;
import com.egin.user.model.mapper.EmployeeCreateRequestToEmployeeEntityMapper;
import com.egin.user.model.mapper.EmployeeEntityToEmployeeMapper;
import com.egin.user.model.mapper.EmployeeUpdateRequestToEmployeeEntityMapper;
import com.egin.user.model.mapper.ListEmployeeEntityToListEmployeeMapper;
import com.egin.user.repository.EmployeeRepository;
import com.egin.user.service.employee.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final StoreService storeService;
    private final BranchService branchService;
    private final PasswordEncoder passwordEncoder;

    public EmployeeServiceImpl(
            final EmployeeRepository employeeRepository,
            final StoreService storeService,
            final BranchService branchService,
            final PasswordEncoder passwordEncoder
    ) {
        this.employeeRepository = employeeRepository;
        this.storeService = storeService;
        this.branchService = branchService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public Employee createStoreEmployee(final EmployeeCreateRequest request) {

        if (employeeRepository.existsByEmail(request.getEmail())) {
            throw new EmployeeAlreadyExistsException("Employee with email " + request.getEmail() + " already exists");
        }

        final Store store = storeService.getStoreById(request.getStoreId());

        final EmployeeEntity employeeEntity = EmployeeCreateRequestToEmployeeEntityMapper
                .toEmployeeEntity(request, StoreToStoreEntityMapper.toStoreEntity(store), null);

        employeeEntity.setPassword(passwordEncoder.encode(request.getPassword()));

        final EmployeeEntity savedEmployee = employeeRepository.save(employeeEntity);

        return EmployeeEntityToEmployeeMapper.toEmployee(savedEmployee);
    }

    @Override
    @Transactional
    public Employee createBranchEmployee(final EmployeeCreateRequest request) {

        if (employeeRepository.existsByEmail(request.getEmail())) {
            throw new EmployeeAlreadyExistsException("Employee with email " + request.getEmail() + " already exists");
        }

        final Branch branch = branchService.getBranchById(request.getBranchId());

        final EmployeeEntity employeeEntity = EmployeeCreateRequestToEmployeeEntityMapper
                .toEmployeeEntity(
                        request,
                        StoreToStoreEntityMapper.toStoreEntity(branch.getStore()),
                        BranchToBranchEntityMapper.toBranchEntity(branch)
                );

        employeeEntity.setPassword(passwordEncoder.encode(request.getPassword()));

        final EmployeeEntity savedEmployee = employeeRepository.save(employeeEntity);

        return EmployeeEntityToEmployeeMapper.toEmployee(savedEmployee);
    }

    @Override
    @Transactional
    public Employee updateEmployee(final String employeeId, final EmployeeUpdateRequest request) {

        final EmployeeEntity employeeEntity = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + employeeId));

        EmployeeUpdateRequestToEmployeeEntityMapper.mapForUpdate(employeeEntity, request);

        final EmployeeEntity updatedEmployee = employeeRepository.save(employeeEntity);

        return EmployeeEntityToEmployeeMapper.toEmployee(updatedEmployee);
    }

    @Override
    @Transactional
    public void deleteEmployee(final String employeeId) {

        final EmployeeEntity employeeEntity = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + employeeId));

        employeeRepository.delete(employeeEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomPage<Employee> findStoreEmployees(final String storeId, final EmployeeByStorePagingRequest pagingRequest) {

        // Store'un varlığını kontrol et (bulunamazsa exception fırlatır)
        storeService.getStoreById(storeId);

        final Page<EmployeeEntity> employeeEntityPage = employeeRepository.findByStoreId(
                storeId,
                pagingRequest.toPageable()
        );

        final List<Employee> employees = ListEmployeeEntityToListEmployeeMapper
                .toEmployeeList(employeeEntityPage.getContent());

        return CustomPage.of(employees, employeeEntityPage);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomPage<Employee> findBranchEmployees(final String branchId, final EmployeeByBranchPagingRequest pagingRequest) {

        // Branch'in varlığını kontrol et (bulunamazsa exception fırlatır)
        branchService.getBranchById(branchId);

        final Page<EmployeeEntity> employeeEntityPage = employeeRepository.findByBranchId(
                branchId,
                pagingRequest.toPageable()
        );

        final List<Employee> employees = ListEmployeeEntityToListEmployeeMapper
                .toEmployeeList(employeeEntityPage.getContent());

        return CustomPage.of(employees, employeeEntityPage);
    }

    @Override
    @Transactional(readOnly = true)
    public Employee getEmployeeById(final  String employeeId) {

        final EmployeeEntity employeeEntity = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + employeeId));

        return EmployeeEntityToEmployeeMapper.toEmployee(employeeEntity);
    }
}

