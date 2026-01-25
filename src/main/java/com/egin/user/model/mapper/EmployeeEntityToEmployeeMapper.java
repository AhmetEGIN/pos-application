package com.egin.user.model.mapper;

import com.egin.branch.model.mapper.BranchEntityToBranchMapper;
import com.egin.store.model.mapper.StoreEntityToStoreMapper;
import com.egin.user.model.Employee;
import com.egin.user.model.entity.EmployeeEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EmployeeEntityToEmployeeMapper {

    public Employee toEmployee(EmployeeEntity employeeEntity) {
        return Employee.builder()
                .id(employeeEntity.getId())
                .email(employeeEntity.getEmail())
                .password(employeeEntity.getPassword())
                .firstName(employeeEntity.getFirstName())
                .lastName(employeeEntity.getLastName())
                .userStatus(employeeEntity.getUserStatus())
                .userType(employeeEntity.getUserType())
                .department(employeeEntity.getDepartment())
                .position(employeeEntity.getPosition())
                .phoneNumber(employeeEntity.getPhoneNumber())
                .hireDate(employeeEntity.getHireDate())
                .salary(employeeEntity.getSalary())
                .store(employeeEntity.getStore() != null ? StoreEntityToStoreMapper.toStore(employeeEntity.getStore()) : null)
                .branch(employeeEntity.getBranch() != null ? BranchEntityToBranchMapper.toBranch(employeeEntity.getBranch()) : null)
                .createdAt(employeeEntity.getCreatedAt())
                .updatedAt(employeeEntity.getUpdatedAt())
                .createdBy(employeeEntity.getCreatedBy())
                .updatedBy(employeeEntity.getUpdatedBy())
                .build();
    }

}

