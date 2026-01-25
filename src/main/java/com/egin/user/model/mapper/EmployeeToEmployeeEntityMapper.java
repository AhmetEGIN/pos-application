package com.egin.user.model.mapper;

import com.egin.branch.model.mapper.BranchToBranchEntityMapper;
import com.egin.store.model.mapper.StoreToStoreEntityMapper;
import com.egin.user.model.Employee;
import com.egin.user.model.entity.EmployeeEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EmployeeToEmployeeEntityMapper {

    public EmployeeEntity toEmployeeEntity(Employee employee) {
        return EmployeeEntity.builder()
                .id(employee.getId())
                .email(employee.getEmail())
                .password(employee.getPassword())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .userStatus(employee.getUserStatus())
                .userType(employee.getUserType())
                .department(employee.getDepartment())
                .position(employee.getPosition())
                .phoneNumber(employee.getPhoneNumber())
                .hireDate(employee.getHireDate())
                .salary(employee.getSalary())
                .store(employee.getStore() != null ? StoreToStoreEntityMapper.toStoreEntity(employee.getStore()) : null)
                .branch(employee.getBranch() != null ? BranchToBranchEntityMapper.toBranchEntity(employee.getBranch()) : null)
                .createdAt(employee.getCreatedAt())
                .updatedAt(employee.getUpdatedAt())
                .createdBy(employee.getCreatedBy())
                .updatedBy(employee.getUpdatedBy())
                .build();
    }

}

